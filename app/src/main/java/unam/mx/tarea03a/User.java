package unam.mx.tarea03a;

import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable {
    private int id;
    private String name, password;
    // 2 = mujer, 1 = hombre
    private int genre, squats, pushUps, crunches;
    private float imc;
    private int hearRate;

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(password);
        dest.writeInt(genre);
        dest.writeInt(squats);
        dest.writeInt(pushUps);
        dest.writeInt(crunches);
        dest.writeFloat(imc);
        dest.writeInt(hearRate);

    }

    public User(){};

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public User(Parcel source){
        id = source.readInt();
        name = source.readString();
        password = source.readString();
        genre = source.readInt();
        squats = source.readInt();
        crunches = source.readInt();
        imc = source.readFloat();
        hearRate = source.readInt();
    }

    public User(String name, String password, int squats, int pushUps,
                int crunches, float imc, int hearRate) {
        this.name = name;
        this.password = password;
        this.squats = squats;
        this.pushUps = pushUps;
        this.crunches = crunches;
        this.imc = imc;
        this.hearRate = hearRate;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHearRate() {
        return hearRate;
    }

    public void setHearRate(int hearRate) {
        this.hearRate = hearRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSquats() {
        return squats;
    }

    public void setSquats(int squats) {
        this.squats = squats;
    }

    public int getPushUps() {
        return pushUps;
    }

    public void setPushUps(int pushUps) {
        this.pushUps = pushUps;
    }

    public int getCrunches() {
        return crunches;
    }

    public void setCrunches(int crunches) {
        this.crunches = crunches;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }
}
