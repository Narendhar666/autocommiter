package com.bot.runner;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AutoCommitBot extends TimerTask {

	private static final String WORKSPACE_LOCATION = "D:/workspace/vms_ui/vms_ui/";
	private static final String[] FILE_NAMES = { "meeting.html", "visitor.html", "service.js", "demo.js" };
	private static final String[] FILE_CONTENTS = {
			"In a fringilla nunc. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras eleifend varius mi sit amet condimentum. Ut semper bibendum nisi. Morbi vel risus id magna lobortis mattis vel vitae lacus. Pellentesque mollis sapien ac sem euismod bibendum et eu diam. Vestibulum et mattis massa. Maecenas ullamcorper velit sed vestibulum ornare. Duis tincidunt malesuada sem, volutpat varius magna iaculis scelerisque. Nullam arcu est, hendrerit sed hendrerit accumsan, sagittis ut leo. Curabitur suscipit ornare dolor euismod laoreet. Sed sit amet rutrum nisl. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.",
			"Vestibulum pharetra id sapien non aliquet. In volutpat dapibus ex vitae efficitur. Pellentesque pellentesque felis egestas efficitur eleifend. Donec commodo eget arcu in placerat. Phasellus sollicitudin nisi dictum auctor eleifend. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer facilisis quis enim quis interdum. Morbi non urna tellus. Maecenas imperdiet rhoncus felis, eu vulputate mauris porta a. Aliquam congue vulputate ex, vitae facilisis nibh porta ut. Nulla ullamcorper bibendum lectus at tempus.",
			"Pellentesque ligula neque, ultrices eget metus sed, lacinia rhoncus elit. Sed eget pharetra nunc, at faucibus quam. Vivamus pellentesque commodo mi quis pellentesque. Maecenas nec enim placerat, porttitor nibh a, dapibus tortor. Quisque vestibulum condimentum orci, in accumsan mauris hendrerit in. Pellentesque vel massa non justo sollicitudin varius a eget turpis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nulla eget nulla efficitur, eleifend risus non, rutrum enim. Integer maximus cursus urna posuere cursus. Nam vel tincidunt erat. In euismod elit magna, at porttitor lacus tincidunt imperdiet. Nulla a mauris pulvinar, euismod tortor sit amet, tincidunt purus.",
			"Curabitur vulputate bibendum enim vitae eleifend. Proin justo dui, aliquet semper quam quis, aliquet tincidunt massa. Sed bibendum et dui id pretium. Nulla dapibus commodo dapibus. Suspendisse congue arcu non purus sodales, id facilisis nunc aliquam. In vel scelerisque nulla. Duis id mi id justo fermentum vestibulum quis a erat. Morbi a ante pharetra, molestie felis et, sagittis lectus. Praesent sit amet nulla ac diam hendrerit feugiat. Proin dapibus tempus metus, vitae aliquet massa egestas et. Mauris pretium volutpat sem" };
	private static final String COMMIT_PREFIX = "Commit changes on: ";
	private static final String[] COMMIT_MESSAGES = { "Added adhoc meeting fixes", "fixed payload issue",
			"issue checker for date in meeting", "Changed indendation" };
	private static final String GIT_ADD = "git add ";
	private static final String GIT_COMMIT = "git commit -m '" + COMMIT_PREFIX;
	private static final String GIT_PUSH = "git push ";
	
	static AutoCommitBot commitBot = null;

	public static void main(String[] args) {
		commitBot = new AutoCommitBot();
		Timer timer = new Timer();
		Random rnd = new Random();
		int milliSeconds = 1000000 + rnd.nextInt(9000000);
		//timer.schedule(commitBot, 0, 2000);
		try {
			commitBot.commitBot();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public String CurrentDate() {

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        String currentTime = sdf.format(dt);
        return currentTime;

    }

	public void commitBot() throws IOException, AWTException, InterruptedException {
		Thread.sleep(2000);
		Random random = new Random();
		int index = random.nextInt(FILE_NAMES.length);
		String fileName = WORKSPACE_LOCATION + FILE_NAMES[index];
		File file = new File(fileName);
		if( ! file.exists()){
			file.createNewFile();		    
		}
		Files.write(Paths.get(fileName), FILE_CONTENTS[index].getBytes());
		Robot bot = new Robot();
		
		
		Runtime.getRuntime().exec("explorer.exe /select," + WORKSPACE_LOCATION);
		Thread.sleep(5000);
		bot.keyPress(KeyEvent.VK_ALT);
		bot.keyPress(KeyEvent.VK_SPACE);
		bot.keyRelease(KeyEvent.VK_ALT);
		bot.keyRelease(KeyEvent.VK_SPACE);
		Thread.sleep(2000);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		
		bot.keyPress(KeyEvent.VK_ENTER);
		bot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(2000);		
		
		//select path
		bot.keyPress(KeyEvent.VK_CONTROL);
		bot.keyPress(KeyEvent.VK_L);
		bot.keyRelease(KeyEvent.VK_CONTROL);
		bot.keyRelease(KeyEvent.VK_L);
		Thread.sleep(2000);
		//type workspace location
		for (int i=0; i < WORKSPACE_LOCATION.length(  ); i++) {
			printLetter(bot, WORKSPACE_LOCATION.charAt(i) + "");
		}
		bot.keyPress(KeyEvent.VK_ENTER);
		bot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(3000);
		
		bot.mousePress(InputEvent.BUTTON3_MASK);
		bot.mouseRelease(InputEvent.BUTTON3_MASK);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);		
		Thread.sleep(1000);
		
		bot.keyPress(KeyEvent.VK_DOWN);
		bot.keyRelease(KeyEvent.VK_DOWN);		
		Thread.sleep(1000);
		
		bot.keyPress(KeyEvent.VK_ENTER);
		bot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(2000);
		//select path
		/*bot.keyPress(KeyEvent.VK_CONTROL);
		bot.keyPress(KeyEvent.VK_L);
		bot.keyRelease(KeyEvent.VK_CONTROL);
		bot.keyRelease(KeyEvent.VK_L);
		Thread.sleep(2000);
		//select path
		bot.keyPress(KeyEvent.VK_C);
		bot.keyRelease(KeyEvent.VK_C);
		bot.keyPress(KeyEvent.VK_M);
		bot.keyRelease(KeyEvent.VK_M);
		bot.keyPress(KeyEvent.VK_D);
		bot.keyRelease(KeyEvent.VK_D);
		Thread.sleep(2000);
		bot.keyPress(KeyEvent.VK_ENTER);
		bot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(6000);*/
		//git add
		for (int i=0; i < GIT_ADD.length(  ); i++) {
			printLetter(bot, GIT_ADD.charAt(i) + "");
		}
		Thread.sleep(2000);
		
		for (int i=0; i < FILE_NAMES[index].length(  ); i++) {
			printLetter(bot, FILE_NAMES[index].charAt(i) + "");
		}
		Thread.sleep(2000);
		bot.keyPress(KeyEvent.VK_ENTER);
		bot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(2000);
		//git commit
		for (int i=0; i < GIT_COMMIT.length(  ); i++) {
			printLetter(bot, GIT_COMMIT.charAt(i) + "");
		}
		Thread.sleep(2000);
		for (int i=0; i < COMMIT_MESSAGES[index].length(  ); i++) {
			printLetter(bot, COMMIT_MESSAGES[index].charAt(i) + "");
		}
		
		printLetter(bot, "'");
		Thread.sleep(2000);
		bot.keyPress(KeyEvent.VK_ENTER);
		bot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(1000);
		
		//git push
		for (int i=0; i < GIT_PUSH.length(  ); i++) {
			printLetter(bot, GIT_PUSH.charAt(i) + "");
		}
		Thread.sleep(2000);
		bot.keyPress(KeyEvent.VK_ENTER);
		bot.keyRelease(KeyEvent.VK_ENTER);
		    
       
	}
	
	public static void printLetter(Robot bot, String charector) {
		switch (charector) {
		case "a":
			bot.keyPress(KeyEvent.VK_A);
            bot.keyRelease(KeyEvent.VK_A);
			break;
		case "b":
			bot.keyPress(KeyEvent.VK_B);
            bot.keyRelease(KeyEvent.VK_B);
			break;
		case "c":
			bot.keyPress(KeyEvent.VK_C);
            bot.keyRelease(KeyEvent.VK_C);
			break;
		case "d":
			bot.keyPress(KeyEvent.VK_D);
            bot.keyRelease(KeyEvent.VK_D);
			break;
		case "e":
			bot.keyPress(KeyEvent.VK_E);
            bot.keyRelease(KeyEvent.VK_E);
			break;
		case "f":
			bot.keyPress(KeyEvent.VK_F);
            bot.keyRelease(KeyEvent.VK_F);
			break;
		case "g":
			bot.keyPress(KeyEvent.VK_G);
            bot.keyRelease(KeyEvent.VK_G);
			break;
		case "h":
			bot.keyPress(KeyEvent.VK_H);
            bot.keyRelease(KeyEvent.VK_H);
			break;
		case "i":
			bot.keyPress(KeyEvent.VK_I);
            bot.keyRelease(KeyEvent.VK_I);
			break;
		case "j":
			bot.keyPress(KeyEvent.VK_J);
            bot.keyRelease(KeyEvent.VK_J);
			break;
		case "k":
			bot.keyPress(KeyEvent.VK_K);
            bot.keyRelease(KeyEvent.VK_K);
			break;
		case "l":
			bot.keyPress(KeyEvent.VK_L);
            bot.keyRelease(KeyEvent.VK_L);
			break;
		case "m":
			bot.keyPress(KeyEvent.VK_M);
            bot.keyRelease(KeyEvent.VK_M);
			break;
		case "n":
			bot.keyPress(KeyEvent.VK_N);
            bot.keyRelease(KeyEvent.VK_N);
			break;
		case "o":
			bot.keyPress(KeyEvent.VK_O);
            bot.keyRelease(KeyEvent.VK_O);
			break;
		case "p":
			bot.keyPress(KeyEvent.VK_P);
            bot.keyRelease(KeyEvent.VK_P);
			break;
		case "q":
			bot.keyPress(KeyEvent.VK_Q);
            bot.keyRelease(KeyEvent.VK_Q);
			break;
		case "r":
			bot.keyPress(KeyEvent.VK_R);
            bot.keyRelease(KeyEvent.VK_R);
			break;
		case "s":
			bot.keyPress(KeyEvent.VK_S);
            bot.keyRelease(KeyEvent.VK_S);
			break;
		case "t":
			bot.keyPress(KeyEvent.VK_T);
            bot.keyRelease(KeyEvent.VK_T);
			break;
		case "u":
			bot.keyPress(KeyEvent.VK_U);
            bot.keyRelease(KeyEvent.VK_U);
			break;
		case "v":
			bot.keyPress(KeyEvent.VK_V);
            bot.keyRelease(KeyEvent.VK_V);
			break;
		case "w":
			bot.keyPress(KeyEvent.VK_W);
            bot.keyRelease(KeyEvent.VK_W);
			break;
		case "x":
			bot.keyPress(KeyEvent.VK_X);
            bot.keyRelease(KeyEvent.VK_X);
			break;
		case "y":
			bot.keyPress(KeyEvent.VK_Y);
            bot.keyRelease(KeyEvent.VK_Y);
			break;
		case "z":
			bot.keyPress(KeyEvent.VK_Z);
            bot.keyRelease(KeyEvent.VK_Z);
			break;
		case "A":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_A);
            bot.keyRelease(KeyEvent.VK_A);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "B":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_B);
            bot.keyRelease(KeyEvent.VK_B);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "C":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_C);
            bot.keyRelease(KeyEvent.VK_C);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "D":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_D);
            bot.keyRelease(KeyEvent.VK_D);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "E":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_E);
            bot.keyRelease(KeyEvent.VK_E);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "F":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_F);
            bot.keyRelease(KeyEvent.VK_F);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "G":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_G);
            bot.keyRelease(KeyEvent.VK_G);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "H":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_H);
            bot.keyRelease(KeyEvent.VK_H);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "I":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_I);
            bot.keyRelease(KeyEvent.VK_I);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "J":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_J);
            bot.keyRelease(KeyEvent.VK_J);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "K":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_K);
            bot.keyRelease(KeyEvent.VK_K);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "L":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_L);
            bot.keyRelease(KeyEvent.VK_L);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "M":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_M);
            bot.keyRelease(KeyEvent.VK_M);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "N":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_N);
            bot.keyRelease(KeyEvent.VK_N);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "O":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_O);
            bot.keyRelease(KeyEvent.VK_O);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "P":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_P);
            bot.keyRelease(KeyEvent.VK_P);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "Q":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_Q);
            bot.keyRelease(KeyEvent.VK_Q);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "R":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_R);
            bot.keyRelease(KeyEvent.VK_R);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "S":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_S);
            bot.keyRelease(KeyEvent.VK_S);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "T":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_T);
            bot.keyRelease(KeyEvent.VK_T);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "U":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_U);
            bot.keyRelease(KeyEvent.VK_U);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "V":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_V);
            bot.keyRelease(KeyEvent.VK_V);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "W":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_W);
            bot.keyRelease(KeyEvent.VK_W);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "X":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_X);
            bot.keyRelease(KeyEvent.VK_X);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "Y":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_Y);
            bot.keyRelease(KeyEvent.VK_Y);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "Z":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_Z);
            bot.keyRelease(KeyEvent.VK_Z);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "/":
			bot.keyPress(KeyEvent.VK_SLASH);
            bot.keyRelease(KeyEvent.VK_SLASH);
			break;
		case "_":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_MINUS);
            bot.keyRelease(KeyEvent.VK_MINUS);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "-":
			bot.keyPress(KeyEvent.VK_MINUS);
            bot.keyRelease(KeyEvent.VK_MINUS);
			break;
		case "?":
			bot.keyPress(KeyEvent.VK_SHIFT); //keyCode 16
			bot.keyPress(KeyEvent.VK_SLASH); //keyCode 47
			bot.keyRelease(KeyEvent.VK_SLASH);
			bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "!":
			bot.keyPress(KeyEvent.VK_SHIFT); //keyCode 16
            bot.keyPress(KeyEvent.VK_1); //keycode 49
            bot.keyRelease(KeyEvent.VK_1);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "&":
			bot.keyPress(KeyEvent.VK_AMPERSAND);
            bot.keyRelease(KeyEvent.VK_AMPERSAND);
			break;
		case "*":
			bot.keyPress(KeyEvent.VK_ASTERISK);
            bot.keyRelease(KeyEvent.VK_ASTERISK);
			break;
		case ".":
			bot.keyPress(KeyEvent.VK_PERIOD);
            bot.keyRelease(KeyEvent.VK_PERIOD);
			break;
		case " ":
			bot.keyPress(KeyEvent.VK_SPACE);
            bot.keyRelease(KeyEvent.VK_SPACE);
			break;
		case ",":
			bot.keyPress(KeyEvent.VK_COMMA);
            bot.keyRelease(KeyEvent.VK_COMMA);
			break;
		case ":":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_SEMICOLON);
            bot.keyRelease(KeyEvent.VK_SEMICOLON);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ";":
			bot.keyPress(KeyEvent.VK_SEMICOLON);
            bot.keyRelease(KeyEvent.VK_SEMICOLON);
			break;
		case "'":
			bot.keyPress(KeyEvent.VK_QUOTE);
            bot.keyRelease(KeyEvent.VK_QUOTE);
			break;
		case "\"":
			bot.keyPress(KeyEvent.VK_QUOTEDBL);
            bot.keyRelease(KeyEvent.VK_QUOTEDBL);
			break;
		case "(":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_9);
            bot.keyRelease(KeyEvent.VK_9);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ")":
			bot.keyPress(KeyEvent.VK_SHIFT);
			bot.keyPress(KeyEvent.VK_0);
            bot.keyRelease(KeyEvent.VK_0);
            bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case "0":
			bot.keyPress(KeyEvent.VK_0);
            bot.keyRelease(KeyEvent.VK_0);
			break;
		case "1":
			bot.keyPress(KeyEvent.VK_1);
            bot.keyRelease(KeyEvent.VK_1);
			break;
		case "2":
			bot.keyPress(KeyEvent.VK_2);
            bot.keyRelease(KeyEvent.VK_2);
			break;
		case "3":
			bot.keyPress(KeyEvent.VK_3);
            bot.keyRelease(KeyEvent.VK_3);
			break;
		case "4":
			bot.keyPress(KeyEvent.VK_4);
            bot.keyRelease(KeyEvent.VK_4);
			break;
		case "5":
			bot.keyPress(KeyEvent.VK_5);
            bot.keyRelease(KeyEvent.VK_5);
			break;
		case "6":
			bot.keyPress(KeyEvent.VK_6);
            bot.keyRelease(KeyEvent.VK_6);
			break;
		case "7":
			bot.keyPress(KeyEvent.VK_7);
            bot.keyRelease(KeyEvent.VK_7);
			break;
		case "8":
			bot.keyPress(KeyEvent.VK_8);
            bot.keyRelease(KeyEvent.VK_8);
			break;
		case "9":
			bot.keyPress(KeyEvent.VK_9);
            bot.keyRelease(KeyEvent.VK_9);
			break;
		default:
			break;
		}
	}

	@Override
	public void run() {
        String todaysdate = commitBot.CurrentDate();
        System.out.println(todaysdate);
        /*try {
			commitBot.commitBot();
		} catch (Exception e) {
			e.printStackTrace();
		}*/ 
    }

}
