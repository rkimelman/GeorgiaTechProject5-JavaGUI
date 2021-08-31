package summermusicproject;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.List;
import java.util.Scanner;
import org.jfugue.theory.Note;

import org.jfugue.pattern.NoteProducer;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.player.Player;

public class musicprojectclass extends JPanel implements ActionListener, 
Runnable {

    private final JComboBox<String> combo1 = new JComboBox<String>(
        new String[]{"Add two frequencies", "Find frequency of note that is n steps away", 
        		"Get note for frequency", "Get frequency for note", 
        		"Test if two notes are enharmonically equivalent",
        		"Test if your note is valid", "Play two notes"});
    private final JComboBox<String> combo_2 = new JComboBox<String>(
    		new String [] {"Add two frequencies", "Find frequency of note that is n steps away", 
            		"Get note for frequency", "Get frequency for note", 
            		"Test if two notes are enharmonically equivalent",
            		"Test if your note is valid", "Play two notes"});
    private static Scanner scanner2;
    private double first_frequency;
    private double previous_frequency;
    private String previous_note;
    private double second_frequency;
    private double third_frequency;
    private String first_note;
    private String second_note;
    private String third_note;
    private double frequency_of_other_note;
    private int midi_value;
    private int yes_or_no;
    private double frequency_get_note;
    private double get_frequency_for_note;
    private String combined_frequencies;
    private double sum_of_frequencies;
    private int note_to_use;
    private int n_steps;
    private String note_for_frequency;
    private Player player;
    
    private ComboBoxModel<String>[] models = new ComboBoxModel[2];

    public musicprojectclass() {
    	scanner2 = new Scanner(System.in);
        models[0] = new DefaultComboBoxModel(
            new String[]{"Add two frequencies", "Find frequency of note that is n steps away",
            		"Get note for frequency"});
        models[1] = new DefaultComboBoxModel(
            new String[]{"Get frequency for note", "Test if two notes are enharmonically equivalent", 
            		"Test if your note is valid", "Play two notes"});
        this.add(combo1);
        this.add(combo_2);
        combo_2.setEnabled(false);
        combo1.addActionListener(new ActionListener() {;
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		player = new Player();
            	if(combo1.getSelectedItem().equals("Add two frequencies")) {
            		addTwoFrequencies(false);
        			combo_2.setEnabled(true);
        			combo_2.setModel(models[0]);          		
        }
            	if(combo1.getSelectedItem().equals("Find frequency of note that is n steps away")) {    				
    				findFrequencyOfNoteThatIsNStepsAway(false);
        			combo_2.setEnabled(true);
        			combo_2.setModel(models[0]);
    			}
            	if(combo1.getSelectedItem().equals("Get note for frequency")) {
            		getNoteForFrequency(false);
            		combo_2.setEnabled(true);
            		combo_2.setModel(models[1]);
            	}
            	if(combo1.getSelectedItem().equals("Get frequency for note")) {
            		getFrequencyForNote(false, false);
        			combo_2.setEnabled(true);
        			combo_2.setModel(models[0]);
            	}
            	if(combo1.getSelectedItem().equals("Test if two notes are enharmonically equivalent")) {
            		enharmonicEquivalence(false, false);
            		combo_2.setEnabled(true);
            		combo_2.setModel(models[1]);
            	}
            	if(combo1.getSelectedItem().equals("Test if your note is valid")) {
            		isNoteValid(false, false);
            		combo_2.setEnabled(true);
            		combo_2.setModel(models[1]);
            	}
            	if(combo1.getSelectedItem().equals("Play two notes")) {
            		playTwoNotesMethod(false, false);
            		combo_2.setEnabled(true);
            		combo_2.setModel(models[1]);
            	}
            	}});
        // references: https://www.titanwolf.org/Network/q/7bd11bfe-71de-4aad-ba55-7d44fd6895f0/y &
	// https://www.tabnine.com/code/java/methods/javax.swing.JComboBox/addActionListener
	//wrote gui that lets you experiment with JFugue. Prototyping tool to allow you to play around with JFugue - step to make
        // a more robust program. A lot of redundancy because it was easier to prototype this code. Just wanted to have something functional.
        // next step would be to create a more general program. Since wriing this code i've become aware of best practices in software design
        // and patterns could be a useful replacement for reading in booleans to the methods.
        combo_2.addActionListener(new ActionListener() {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		player = new Player();
        	if(combo1.getSelectedItem().equals("Add two frequencies")) {
        		if(combo_2.getSelectedItem().equals("Add two frequencies")) {
	        		addTwoFrequencies(true);
        		}
        		if(combo_2.getSelectedItem().equals("Find frequency of note that is n steps away")) {
        			findFrequencyOfNoteThatIsNStepsAway(true);
        			System.out.println(frequencyOfOtherNote(sum_of_frequencies,n_steps));
    			}
        		if(combo_2.getSelectedItem().equals("Get note for frequency")){
        			getNoteForFrequency(true);
        		}	
        	}
        	if(combo1.getSelectedItem().equals("Find frequency of note that is n steps away")) {
        		if(combo_2.getSelectedItem().equals("Add two frequencies")) {
        			addTwoFrequencies(true);
           		}
           		if(combo_2.getSelectedItem().equals("Find frequency of note that is n steps away")) {
       				findFrequencyOfNoteThatIsNStepsAway(true);
       			}
           		if(combo_2.getSelectedItem().equals("Get note for frequency")){
           			getNoteForFrequency(true);
           		}
        	}
        	if(combo1.getSelectedItem().equals("Get note for frequency")) {
        		if(combo_2.getSelectedItem().equals("Get frequency for note")) {
        			getFrequencyForNote(true, false);
        		}
            	if(combo_2.getSelectedItem().equals("Test if two notes are enharmonically equivalent")) {
            		enharmonicEquivalence(true, false);
            	}
            	if(combo_2.getSelectedItem().equals("Test if your note is valid")) {
            		System.out.println(Note.isValidNote(note_for_frequency));
            	}
            	if(combo_2.getSelectedItem().equals("Play two notes")) {
            		playTwoNotesMethod(true, false);
            	}
        	}
        	if(combo1.getSelectedItem().equals("Get frequency for note")){
        		if(combo_2.getSelectedItem().equals("Add two frequencies")) {
        			addTwoFrequencies(true);
           		}
           		if(combo_2.getSelectedItem().equals("Find frequency of note that is n steps away")) {
       				findFrequencyOfNoteThatIsNStepsAway(true);
       			}
           		if(combo_2.getSelectedItem().equals("Get note for frequency")){
           			getNoteForFrequency(true);
           		}
        	}
        	if(combo1.getSelectedItem().equals("Test if two notes are enharmonically equivalent")) {
        		if(combo_2.getSelectedItem().equals("Get frequency for note")) {
        			getFrequencyForNote(true, true);
        		}
            	if(combo_2.getSelectedItem().equals("Test if two notes are enharmonically equivalent")) {
            		enharmonicEquivalence(false, true);
            	}
            	if(combo_2.getSelectedItem().equals("Test if your note is valid")) {
            		isNoteValid(true, true);
            	}
            	if(combo_2.getSelectedItem().equals("Play two notes")) {
            		playTwoNotes(first_note, second_note);
            	}
        	}
        	if(combo1.getSelectedItem().equals("Test if your note is valid")) {
        		if(combo_2.getSelectedItem().equals("Get frequency for note")) {
        			getFrequencyForNote(true, false); 		
            	}
            	if(combo_2.getSelectedItem().equals("Test if two notes are enharmonically equivalent")) {
            		enharmonicEquivalence(true, false);
            	}
            	if(combo_2.getSelectedItem().equals("Test if your note is valid")) {
            		System.out.println(Note.isValidNote(first_note));
            	}
            	if(combo_2.getSelectedItem().equals("Play two notes")) {
            		playTwoNotesMethod(true, false);
            	}
        	}
        	if(combo1.getSelectedItem().equals("Play two notes")) {
        		if(combo_2.getSelectedItem().equals("Get frequency for note")) {
        			getFrequencyForNote(true, true);
        				
            	}
            	if(combo_2.getSelectedItem().equals("Test if two notes are enharmonically equivalent")) {
            		enharmonicEquivalence(false, true);
            	}
            	if(combo_2.getSelectedItem().equals("Test if your note is valid")) {
            		isNoteValid(true, true);
            		
            	}
            	if(combo_2.getSelectedItem().equals("Play two notes")) {
            		playTwoNotesMethod(false, true);
            	}
        	}
        }
    	});}   
    public static boolean booleanForFrequency(double frequency) {
    	if(frequency < 16 || frequency > 7902) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public static double frequencyOfOtherNote(double frequency, int number_of_notes_away) {
    	System.out.println(frequency*Math.pow(2, number_of_notes_away/12));
		return frequency*Math.pow(2, number_of_notes_away/12);
	}
    public static void playTwoNotes(String note, String note2) {
		Player player = new Player();
		player.play(note + note2);
	}

    public static int convertToPitch(String note) {
    	  String sym = "";
    	  int oct = 0;
    	  String[][] notes = { {"C"}, {"Db", "C#"}, {"D"}, {"Eb", "D#"}, {"E"},
    	    {"F"}, {"Gb", "F#"}, {"G"}, {"Ab", "G#"}, {"A"}, {"Bb", "A#"}, {"B"} };
    	  char[] splitNote = note.toCharArray();
    	  if (splitNote.length == 2) {
    	    sym += splitNote[0];
    	    oct = splitNote[1];
    	  } else if (splitNote.length == 3) {
    	    sym += Character.toString(splitNote[0]);
    	    sym += Character.toString(splitNote[1]);
    	    oct = splitNote[2];
    	  }
    	  for (int i = 0; i < notes.length; i++)
    	  for (int j = 0; j < notes[i].length; j++) {
    	    if (notes[i][j].equals(sym)) {
    	        return Character.getNumericValue(oct) * 12 + i;
    	    }
    	  }	  
    	  return -1;
    	  // reference: https://stackoverflow.com/questions/35690046/converting-musical-note-strings-to-midi-pitch-number/35690979
    	}
    public boolean isNoteValid(boolean isTherePreviousNote, boolean areThereTwoPreviousNotes) {
    	if(areThereTwoPreviousNotes) {
    		System.out.println("Which note would you like to use?");
			note_to_use = -1;
			while(note_to_use != 1 && note_to_use != 2) {
				System.out.println("Please enter 1 or 2");
				note_to_use = scanner2.nextInt();
			}
			if(note_to_use == 1) {
				System.out.println(Note.isValidNote(first_note));
				return Note.isValidNote(first_note);
			}
			else if(note_to_use == 2) {
				System.out.println(Note.isValidNote(second_note));
				return Note.isValidNote(second_note);
			}	
    	}
    	if(isTherePreviousNote) {
    		first_note = previous_note;
    	}
    	else {
    		System.out.println("Please enter what you would like to test is a valid note");
    		first_note = scanner2.next();
    	}
		System.out.println(Note.isValidNote(first_note));
		return Note.isValidNote(first_note);
    }
    public double addTwoFrequencies(boolean isTherePreviousFrequency) {
    	first_frequency = -1;
		second_frequency = -1;
		third_frequency = -1;
		if(isTherePreviousFrequency) {
    		first_frequency = previous_frequency;
    	}
		else {
			System.out.println("What is your first frequency?");
			while (booleanForFrequency(first_frequency)) {
				first_frequency = scanner2.nextDouble();
			}
		}
	    System.out.println("What is your second frequency?");
	   	while(booleanForFrequency(second_frequency)) {
	   		second_frequency = scanner2.nextDouble();
	   	}
	   	String first_frequency_string = String.valueOf(first_frequency);
		String second_frequency_string = String.valueOf(second_frequency);
		combined_frequencies = "m" + first_frequency_string + "m" + second_frequency_string;
		sum_of_frequencies = first_frequency + second_frequency;
		if(!isTherePreviousFrequency) {
			previous_frequency = sum_of_frequencies;
		}
		System.out.println("sum: " + sum_of_frequencies);
		player.play(combined_frequencies);
		return sum_of_frequencies;
    }
    public String getNoteForFrequency(boolean isTherePreviousFrequency) {
    	frequency_get_note = -1;
    	if(isTherePreviousFrequency) {
    		frequency_get_note = previous_frequency;
    	}
    	else {
    		System.out.println("What is your frequency?");
    		while (booleanForFrequency(frequency_get_note)) {
    			frequency_get_note = scanner2.nextDouble();
    			if(booleanForFrequency(frequency_get_note)) {
    				System.out.println("Please enter a valid frequency");
    			}
    		}
    	}
		// jfugue sets A5 = 440 Hz (for reference)
		midi_value = (int) (12*Math.log(frequency_get_note/440)/Math.log(2) + 69);
		note_for_frequency = Note.getToneString((byte) midi_value);
		System.out.println(note_for_frequency);
		previous_note = note_for_frequency;
		return note_for_frequency;
    }
    public double getFrequencyForNote(boolean isTherePreviousNote, boolean areThereTwoPreviousNotes) {
    	if(areThereTwoPreviousNotes) {
    		System.out.println("Which note would you like to use?");
			note_to_use = -1;
			while(note_to_use != 1 && note_to_use != 2) {
				System.out.println("Please enter 1 or 2");
				note_to_use = scanner2.nextInt();
			}
			if(note_to_use == 1) {
				midi_value = convertToPitch(first_note);
			}
			else if(note_to_use == 2) {
				midi_value = convertToPitch(second_note);
			}
			System.out.println(Note.getFrequencyForNote(midi_value));
			get_frequency_for_note = Note.getFrequencyForNote(midi_value);
			return get_frequency_for_note;
    	}
		if(!isTherePreviousNote) {
    	midi_value = -1;
		System.out.println("What is the MIDI value of your note?");
		while (midi_value > 127 || midi_value < 0) {
		    midi_value = scanner2.nextInt();
		    if (midi_value <= 127 || midi_value >= 0 ) {
		    	System.out.println(Note.getFrequencyForNote(midi_value));
		    	get_frequency_for_note = Note.getFrequencyForNote(midi_value);
		    	previous_frequency = get_frequency_for_note;
		    } else {
		    	System.out.println("Please input a valid MIDI value");
		    }
		}
    	}
    	else {
    		get_frequency_for_note = Note.getFrequencyForNote(midi_value);
	    	previous_frequency = get_frequency_for_note;
	    	System.out.println(Note.getFrequencyForNote(midi_value));
    	}
		return Note.getFrequencyForNote(midi_value);
    }
    
    public double findFrequencyOfNoteThatIsNStepsAway(boolean isTherePreviousFrequency) {
    	if(isTherePreviousFrequency) {
    		first_frequency = previous_frequency;
    	}
    	else {
    	System.out.println("What is your first frequency?");
		while (booleanForFrequency(first_frequency)) {
		    first_frequency = scanner2.nextDouble();
		}
    	}
		System.out.println("How many steps away would you like to go?");
		while(n_steps <1 || n_steps > 72) {
		n_steps = scanner2.nextInt();
			if(n_steps <1 || n_steps > 72) {
				System.out.println("Please input a valid number of steps");
			}
		}
		frequency_of_other_note = frequencyOfOtherNote(first_frequency,n_steps);
		if(!isTherePreviousFrequency) {
			previous_frequency = frequency_of_other_note;
		}
		System.out.println(frequency_of_other_note);
		return frequency_of_other_note;
    }
    public void playTwoNotesMethod(boolean isTherePreviousNote, boolean areThereTwoPreviousNotes) {
    	if(areThereTwoPreviousNotes && !isTherePreviousNote) {
    		yes_or_no = -1;
    		System.out.println("Do you want to play the same previous 2 notes? enter 1 for yes or 2 for no");
    		yes_or_no = scanner2.nextInt();
    		while(yes_or_no != 1 && yes_or_no != 2) {
    			System.out.println("Please enter 1 or 2");
    			yes_or_no = scanner2.nextInt();
    		}
    		if(yes_or_no == 1) {
    			playTwoNotes(first_note, second_note);
    		}
   			else {
   				first_note = "Z";
   	    		System.out.println("What is your first note?");
   	    		first_note = scanner2.next();
   	    		while(!Note.isValidNote(first_note)) {
   	    			System.out.println("Please enter a valid note");
   	    			first_note = scanner2.next();
   	    		}
   	    		System.out.println("What is your second note?");
   	    		second_note = "Z";
   	    		second_note = scanner2.next();
   	    		while(!Note.isValidNote(second_note)) {
   	 				System.out.println("Please enter a valid note");
   	     			second_note = scanner2.next();
   	     		}
   	    		playTwoNotes(first_note, second_note);
    		}
    	}
    	if(isTherePreviousNote && !areThereTwoPreviousNotes) {
    		System.out.println("What is your second note?");
        	second_note = scanner2.next();
    		while(!Note.isValidNote(second_note)) {
    				System.out.println("Please enter a valid note");
        			second_note = scanner2.next();
        		}
    		playTwoNotes(first_note, second_note);
    	}
    	if(!isTherePreviousNote && !areThereTwoPreviousNotes) {
    		first_note = "Z";
    		System.out.println("What is your first note?");
    		first_note = scanner2.next();
    		while(!Note.isValidNote(first_note)) {
    			System.out.println("Please enter a valid note");
    			first_note = scanner2.next();
    		}
    		second_note = "Z";
    		System.out.println("What is your second note?");
        	second_note = scanner2.next();
    		while(!Note.isValidNote(second_note)) {
    				System.out.println("Please enter a valid note");
        			second_note = scanner2.next();
        		}
    		playTwoNotes(first_note, second_note);
    	}
		
    }
    public boolean enharmonicEquivalence(boolean isTherePreviousNote, boolean areThereTwoPreviousNotes) {
    	if(isTherePreviousNote) {
    		first_note = previous_note;
    	}
    	if(!isTherePreviousNote && !areThereTwoPreviousNotes) {
    		first_note = "Z";
    		System.out.println("What is your first note?");
    		first_note = scanner2.next();
    		while(!Note.isValidNote(first_note)) {
    			System.out.println("Please enter a valid note");
    			first_note = scanner2.next();
    		}
    	System.out.println("What is your second note?");
    	second_note = "Z";
    	second_note = scanner2.next();
		while(!Note.isValidNote(second_note)) {
				System.out.println("Please enter a valid note");
    			second_note = scanner2.next();
    		}
    	}
    	if(!isTherePreviousNote && areThereTwoPreviousNotes) {
    		yes_or_no = -1;
    		System.out.println("Do you want to test the previous 2 notes? enter 1 for yes or 2 for no");
    		yes_or_no = scanner2.nextInt();
    		while(yes_or_no != 1 && yes_or_no != 2) {
    			System.out.println("Please enter 1 or 2");
    			yes_or_no = scanner2.nextInt();
    		}
    		if(yes_or_no == 1) {
    			System.out.println(Note.isSameNote(first_note, second_note));
    			return Note.isSameNote(first_note, second_note);
    		}
   			else {
   				first_note = "Z";
   				System.out.println("What is your first note?");
   				first_note = scanner2.next();
   	    		while(!Note.isValidNote(first_note)) {
   	    			System.out.println("Please enter a valid note");
   	    			first_note = scanner2.next();
   	    		}
   	    		second_note = "Z";
   	    		System.out.println("What is your second note?");
   	    		second_note = scanner2.next();
   	    		while(!Note.isValidNote(second_note)) {
   	 				System.out.println("Please enter a valid note");
   	     			second_note = scanner2.next();
   	     		}
   	    		System.out.println(Note.isSameNote(first_note, second_note));
   	    		return Note.isSameNote(first_note, second_note);
    		}
    	}
		System.out.println(Note.isSameNote(first_note, second_note));
		return Note.isSameNote(first_note, second_note);
    }
    public void run() {
        JFrame f = new JFrame("ComboTest");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        // reference: https://stackoverflow.com/questions/18815792/creating-jcomboboxes-at-runtime
    }
    public static void main(String[] args) {	
        EventQueue.invokeLater(new musicprojectclass());
        // reference: https://www.tabnine.com/code/java/methods/java.awt.EventQueue/invokeLater
    }
}