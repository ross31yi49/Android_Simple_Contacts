package com.example.supi.contactlist;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Supi on 2016/1/9.
 */
public class personData  implements Parcelable {

    private String name;
    private String phoneNumber;
    private String email;

    //consteuctor
    public personData(){

    }

    public personData(String[] readIn){
        name = readIn[0];
        phoneNumber = readIn[1];
        email = readIn[2];
    }

    public personData(String nameIn,String phoneNumberIn,String emailIn){
        name = nameIn;
        phoneNumber = phoneNumberIn;
        email = emailIn;
    }

    //setter
    public void setName(String nameIn){
        name = nameIn;
    }

    public void setPhoneNumber(String phoneNumberIn){
        phoneNumber = phoneNumberIn;
    }

    public void  setEmail(String emailIn){
        email = emailIn;
    }

    //getter

    public String getName(){return name;}

    public String getPhoneNumber(){return phoneNumber;}

    public String getEmail(){return email;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeString(email);
    }

    public static final Parcelable.Creator<personData> CREATOR = new Creator<personData>() {
        @Override
        public personData createFromParcel(Parcel source) {
            return new personData(source.readString(), source.readString(), source.readString());
        }

        @Override
        public personData[] newArray(int size) {
            return new personData[size];
        }
    };
}
