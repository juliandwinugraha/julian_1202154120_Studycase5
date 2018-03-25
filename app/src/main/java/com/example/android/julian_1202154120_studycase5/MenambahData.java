package com.example.android.julian_1202154120_studycase5;

public class MenambahData {
    //mendeklarasi variable
    String todo, desc, priority;

    //konstruktor
    public MenambahData(String todo, String desc, String priority){
        this.todo=todo;
        this.desc=desc;
        this.priority=priority;
    }

    //method setter dan getter untuk to do , description dan priority
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return priority;
    }

    public void setPrior(String priority) {
        this.priority = priority;
    }
}
