package us.sourcefoundry.gutenberg.factories;

import us.sourcefoundry.gutenberg.commands.Command;
import us.sourcefoundry.gutenberg.commands.Unknown;
import us.sourcefoundry.gutenberg.commands.add.Add;
import us.sourcefoundry.gutenberg.commands.build.Build;
import us.sourcefoundry.gutenberg.commands.init.Init;
import us.sourcefoundry.gutenberg.commands.listinventory.ListInventory;
import us.sourcefoundry.gutenberg.commands.removeinventory.RemoveInventory;
import us.sourcefoundry.gutenberg.commands.root.RootCommand;
import us.sourcefoundry.gutenberg.utils.DependencyInjector;

/**
 * This factory creates a command object from the command line arg specified by the user.
 */
public class CommandFactory extends AbstractFactory<Command> {

    //Does not return anything. The CLI is required.
    @Override
    public Command newInstance() {
        return null;
    }

    /**
     * New instance from the command line argument.  Returns a default of unknown command.
     *
     * @param cliCommand The command line.
     * @return Command
     */
    public Command newInstance(String cliCommand) {
        switch (cliCommand.toLowerCase()) {
            case "add":
                return this.getCommandInstance(Add.class);
            case "list":
                return this.getCommandInstance(ListInventory.class);
            case "init":
                return this.getCommandInstance(Init.class);
            case "build":
                return this.getCommandInstance(Build.class);
            case "remove":
                return this.getCommandInstance(RemoveInventory.class);
            case "":
                //If the command is not provided in the command line, then assume root command.
                return this.getCommandInstance(RootCommand.class);
            default:
                //If anything other than a known command or empty string is provided, its unkown and should error.
                return this.getCommandInstance(Unknown.class);
        }
    }

    /**
     * Runs the new instance of the command through the dependency injection.
     *
     * @param classOfT The class to create through the injector.
     * @param <T>      The type.
     * @return T
     */
    private <T extends Command> T getCommandInstance(Class<T> classOfT) {
        return DependencyInjector.getInstance(classOfT);
    }
}
