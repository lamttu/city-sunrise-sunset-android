package au.edu.swin.sdmd.suncalculatorjava;

import android.os.Parcel;
import android.os.Parcelable;

class AULocation implements Parcelable {
    private String cityName;
    private double latitude;
    private double longtitude;
    private int timezone;

    public AULocation(String cityName, double latitude, double longtitude, int timezone) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.timezone = timezone;
    }

    protected AULocation(Parcel in) {
        cityName = in.readString();
        latitude = in.readDouble();
        longtitude = in.readDouble();
        timezone = in.readInt();
    }

    public static final Creator<AULocation> CREATOR = new Creator<AULocation>() {
        @Override
        public AULocation createFromParcel(Parcel in) {
            return new AULocation(in);
        }

        @Override
        public AULocation[] newArray(int size) {
            return new AULocation[size];
        }
    };

    public String getCityName() {
        return cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public int getTimezone() {
        return timezone;
    }

    @Override
    public String toString() {
        return  cityName + ',' + latitude +
                "," + longtitude +
                "," + timezone + "\r\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cityName);
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longtitude);
        parcel.writeInt(this.timezone);
    }
}
