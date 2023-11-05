package Menu;

public class GameMenu implements Menu {
    
    @Override
    public void printWelcome(){
        System.out.println("██████╗ ██╗      █████╗  ██████╗██╗  ██╗     ██╗ █████╗  ██████╗██╗  ██╗");
        System.out.println("██╔══██╗██║     ██╔══██╗██╔════╝██║ ██╔╝     ██║██╔══██╗██╔════╝██║ ██╔╝");
        System.out.println("██████╔╝██║     ███████║██║     █████╔╝      ██║███████║██║     █████╔╝ ");
        System.out.println("██╔══██╗██║     ██╔══██║██║     ██╔═██╗ ██   ██║██╔══██║██║     ██╔═██╗ ");
        System.out.println("██████╔╝███████╗██║  ██║╚██████╗██║  ██╗╚█████╔╝██║  ██║╚██████╗██║  ██╗");
        System.out.println("╚═════╝ ╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝ ╚════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝");
        System.out.println("By: MJacker & Alvaroxx1");
    }

    @Override
    public void printGameOver(){
        System.out.println(" ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗ ");
        System.out.println(" ██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗");
        System.out.println(" ██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝");
        System.out.println(" ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗");
        System.out.println(" ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║");
        System.out.println(" ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝");
    }

    public void gameStarting(){
    	System.out.println("\n************************************************");		
        System.out.print("*** The game is starting***");
        Static.Funtions.wait(1000);
        System.out.print(".");
        Static.Funtions.wait(1000);
        System.out.print(".");
        Static.Funtions.wait(1000);
        System.out.println(".");
    	System.out.println("\n************************************************");	
    }

    public void enterToStard(){
        System.out.println("\nPress enter to start.");
		Static.Funtions.scannerObjectString();
    }
}