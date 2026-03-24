// SABOTAGE : This will likely cause a NullPointerException if not handled . 
TextView counterDisplay = null; counterDisplay.setText("0");

The app crashes because the counterDisplay variable is told to set a text but it is not pointing to a TextView element. 
To fix this, there must be a TextView element in the XML file of the main activity and properly connect the correct ID of the label.

// Fix TextView counterDisplay = findViewById(R.id.counterLabel); counterDisplay.setText("0");
