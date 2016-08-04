package com.baranova.pharmacy.command;

import com.baranova.pharmacy.type.TypeCommand;

import java.util.HashMap;

/**
 * Created by Ekaterina on 7/18/16.
 */
public class CommandHelper {

    private static HashMap<TypeCommand,ICommand> commands;
    static {
        commands = new HashMap<TypeCommand, ICommand>();
        commands.put(TypeCommand.AUTHORIZATION, new AutorizationCommand());
        commands.put(TypeCommand.REGISTRATION,new RegistrationCommand());
        commands.put(TypeCommand.CHANGE_LANGUAGE,new ChangeLanguageCommand());
        commands.put(TypeCommand.SEARCH,new SearchCommand());
        commands.put(TypeCommand.SHOW_ORDERS,new OrdersCommand());
        commands.put(TypeCommand.SHOW_RECIPES,new RecipesCommand());
        commands.put(TypeCommand.SHOW_MEDICINES,new MedicinesCommand());
        commands.put(TypeCommand.PREPARE_ORDER,new PrepareOrderCommand());
        commands.put(TypeCommand.NEW_MEDICINE,new NewMedicineCommand());
        commands.put(TypeCommand.DELETE_MEDICINE,new DeleteMedicineCommand());
        commands.put(TypeCommand.PROCEED_UPDATE_MEDICINE,new ProceedUpdateMedicine());
        commands.put(TypeCommand.UPDATE_MEDICINE,new UpdateMedicineCommand());
    }

    public static ICommand getCommand(String commandName) {
        TypeCommand key = TypeCommand.valueOf(commandName.replace('-', '_').toUpperCase());
        ICommand command = commands.get(key);
        return command;
    }

}
