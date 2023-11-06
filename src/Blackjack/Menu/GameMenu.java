package Menu;

public class GameMenu implements Menu {
    
    @Override
    public void title(){
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
        int n = 0;
        while (n < 20){
            Static.Funtions.wait(100);
            System.out.print(".");
            n++;
        }
    	System.out.println("\n************************************************");	
    }

    public void enterToStard(){
        System.out.println("\nPress enter to start.");
		Static.Funtions.scannerObjectString();
    }
}