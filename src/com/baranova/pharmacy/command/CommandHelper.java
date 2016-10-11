package com.baranova.pharmacy.command;

import com.baranova.pharmacy.type.TypeCommand;

import java.util.HashMap;

/**
 * Storing all commands that are used in application in HashMap for convenient access.
 */
public class CommandHelper {

    private static HashMap<TypeCommand,ICommand> commands;

    static {
        commands = new HashMap<>();
        commands.put(TypeCommand.AUTHORIZATION, new AuthorizationCommand());
        commands.put(TypeCommand.REGISTRATION,new RegistrationCommand());
        commands.put(TypeCommand.CHANGE_LANGUAGE,new ChangeLanguageCommand());
        commands.put(TypeCommand.SEARCH,new SearchCommand());
        commands.put(TypeCommand.SHOW_ORDERS,new ShowOrdersCommand());
        commands.put(TypeCommand.SHOW_MEDICINES,new MedicinesCommand());
        commands.put(TypeCommand.PREPARE_ORDER,new PrepareOrderCommand());
        commands.put(TypeCommand.NEW_MEDICINE,new NewMedicineCommand());
        commands.put(TypeCommand.DELETE_MEDICINE,new DeleteMedicineCommand());
        commands.put(TypeCommand.PROCEED_UPDATE_MEDICINE,new ProceedUpdateMedicine());
        commands.put(TypeCommand.UPDATE_MEDICINE,new UpdateMedicineCommand());
        commands.put(TypeCommand.NEW_RECIPE,new NewRecipeCommand());
        commands.put(TypeCommand.SHOW_BUYER_RECIPE,new BuyerRecipesCommand());
        commands.put(TypeCommand.SHOW_DOCTOR_RECIPE,new DoctorRecipesCommand());
        commands.put(TypeCommand.SIGN_OUT,new SignOutCommand());
        commands.put(TypeCommand.RENEW_RECIPE_REQUEST, new RenewRequestCommand());
        commands.put(TypeCommand.NEW_ORDER,new NewOrderCommand());
        commands.put(TypeCommand.SHOW_RENEW_REQUESTS,new ShowRenewRequests());
        commands.put(TypeCommand.RENEW_RECIPE,new RenewRecipeCommand());
        commands.put(TypeCommand.BALANCE_REFILL,new BalanceRefillCommand());
    }

    /**
     * Return appropriate type of command on controller's demand
     * @param commandName name of command receiving from controller
     * @return Command appropriate type of comand
     */
    public static ICommand getCommand(String commandName) {
        TypeCommand key = TypeCommand.valueOf(commandName.replace('-', '_').toUpperCase());
        return commands.get(key);
    }

}
