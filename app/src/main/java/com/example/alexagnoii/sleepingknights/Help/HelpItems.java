package com.example.alexagnoii.sleepingknights.Help;

/**
 * Created by Claude on 2017-11-14.
 */

public class HelpItems {

    private String helpTopic;
    private String helpDescription;
    private int icon;

    public HelpItems(){}

    public HelpItems(String helpTopic, String helpDescription, int icon){
        this.helpTopic = helpTopic;
        this.helpDescription = helpDescription;
        this.icon = icon;
    }

    public String getHelpTopic() {return helpTopic;}

    public void setHelpTopic(String helpTopic) {this.helpTopic = helpTopic;}

    public String getHelpDescription() {return helpDescription;}

    public void setHelpDescription(String helpDescription) {this.helpDescription = helpDescription;}

    public int getIcon() {return icon;}

    public void setIcon(int icon) {this.icon = icon;}
}
