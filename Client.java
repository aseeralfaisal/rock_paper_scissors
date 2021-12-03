import java.io.*;
import java.net.*;

class Client {

	private static String host = "localhost";

	private static Integer port = 8000;

	private static String msgWelcome = "--- Welcome to RockPaperScissors Game ---\n";

	private static String msgRules = "\nRule set:\n - Assume 1 is Rock, 2 is Paper\r\n" + "and 3 is Scissors.\n";

	public static void main(String args[]) throws Exception {

		String input = "";
		String response;

		System.out.println(Client.msgWelcome);

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket(Client.host, Client.port);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		do {

			if (input.equals("-rules")) {
				System.out.println(Client.msgRules);
			}

			System.out.println("Start the game by selecting 1 for Rock 2 for paper, 3 for scissors");
			System.out.print("or type \"-rules\" in order to see the rules: ");
			input = inFromUser.readLine();

		} while (!input.equals("1") && !input.equals("2") && !input.equals("3"));

		// Transmit input to the server and provide some feedback for the user
		outToServer.writeBytes(input + "\n");
		System.out.println("\nYour input (" + input
				+ ") was successfully transmitted to the server. Now just be patient and wait for the result ...");

		response = inFromServer.readLine();
		System.out.println("Response from server: " + response);

		clientSocket.close();

	}
}