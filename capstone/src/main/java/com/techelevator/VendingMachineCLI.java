package com.techelevator;

import com.techelevator.view.Menu;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT = "Exit";
	private static final String FEED_MONEY = "Feed Money";
	private static final String SELECT_PRODUCT = "Select Product";
	private static final String FINISH_TRANSACTION = "Finish Transaction";
	private static final String SECRET_LOG = "";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT,SECRET_LOG };
	private static final String[] PROSES_MENU_OPTIONS = {FEED_MONEY,SELECT_PRODUCT,FINISH_TRANSACTION };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws IOException {
		Inventory inventory = new Inventory();
		VendWallet vendWallet = new VendWallet();
		inventory.loadInventory();
		SalesLog sales = new SalesLog();

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				inventory.printItems();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				first:	while (true) {
					String prosesChoice = (String) menu.getChoiceFromOptionsWithBalance(PROSES_MENU_OPTIONS);
					if (prosesChoice.equals(FEED_MONEY)) {

						BigDecimal moneyFed = vendWallet.feedMoney();

						// added if to make sure log only documents when needed
						if ((moneyFed.doubleValue() > 0)) {
							sales.log("FEED MONEY:", moneyFed, VendWallet.getBalance());
						}
					} else if (prosesChoice.equals(FINISH_TRANSACTION)) {
						BigDecimal vwBalance = VendWallet.getBalance();
						vendWallet.balanceToZero(VendWallet.getBalance());

						//added if to make sure log only documents when needed
						if ((vwBalance.doubleValue() > 0)) {
							sales.log("GIVE CHANGE:", vwBalance, VendWallet.getBalance());
						}
						break;
					} else if (prosesChoice.equals(SELECT_PRODUCT)) {
						inventory.printItems();
						System.out.println("Please enter in the item you would like");
						Scanner input = new Scanner(System.in);
						String inputKey = input.nextLine().toUpperCase();
						BigDecimal vwBalance = VendWallet.getBalance();
						if (!Inventory.getInventory().containsKey(inputKey)){
							System.out.println("\nInvalid item code!");
							continue first;
						}
						inventory.vendItem(inputKey);

						inventory.getInventory().get(inputKey).getQuantity();

						//if needed to avoid nullpointerexception and only log when item is available
						if (Inventory.getInventory().containsKey(inputKey)
								&& inventory.getInventory().get(inputKey).getQuantity() > 0) {
							sales.log(inventory.getInventory().get(inputKey).getName(), vwBalance, VendWallet.getBalance());
						}
					}
				}

			}else if (choice.equals(MAIN_MENU_EXIT)) {
				//added \n for looks
				System.out.println("\nHave a nice day!");
				break;

			} else if (choice.equals(SECRET_LOG)){
				sales.salesReportOut();
				//added \n for looks
				System.out.println("\nSales report printed!");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
