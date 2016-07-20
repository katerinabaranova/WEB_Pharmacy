package com.baranova.pharmacy.command;

import com.baranova.pharmacy.enum_classes.TypeCommand;

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
    }

    public static ICommand getCommand(String commandName) {
        TypeCommand key = TypeCommand.valueOf(commandName.replace('-', '_').toUpperCase());
        ICommand command = commands.get(key);
        return command;
    }

}
