package com.techjini.constraintlayoutsample;

/**
 * Created by Ashif on 19/4/17,April,2017
 * TechJini Solutions
 * Banglore,India
 */

public class RandomModel {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean randomMethod(){
        return false;
        //does nothing
    }
    public int dumbMethod(){
        return 7;
    }

    public void anonymousMethod(){
        return;
    }

    public String methodWhichReturnString(String someString){
        return "howdy";
    }
    public String exceptionMethod(String someString)  {
        if (!someString.equals("asif")){
            return someString;
        }
        else return "hi".concat(someString);
    }

    public String sampleMethod(){
        return "ashif";
    }

    public RandomModel returnsObject(){
        return new RandomModel();
    }

}
