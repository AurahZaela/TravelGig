package com.synex.domain;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    
    private int hotelId; //communicates with hotel management to fetch hotel details
    private int hotelRoomId;
    private int noRooms;
    
    @JoinTable(name="booking_guest",
        joinColumns = {@JoinColumn(name="booking_id")},
        inverseJoinColumns = {@JoinColumn(name="guest_id")}
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Guest> guests;
    
    private String checkInDate;     
    private String checkOutDate;    
    private String bookedOnDate;    
    
    private String status;          
    
    private float  price;
    private float  discount;    
    private String customerMobile;  // USE this to identify the customer who booked
    private String roomType;
    
    private String userName;        // Save the userName for searching the Bookings
    private String userEmail;       // Save the userEmail for searching the Bookings
    
    private float  taxRateInPercent;
    private float  finalCharges;
    
    private float  bonanzaDiscount;
    private float  totalSavings;
    
    public Booking() {}
    
    public Booking(int bookingId, int hotelId, int hotelRoomId, int noRooms, Set<Guest> guests, String checkInDate,
            String checkOutDate, String bookedOnDate, String status, float price, float discount, String customerMobile,
            String roomType, String userName, String userEmail, float taxRateInPercent, float finalCharges,
            float bonanzaDiscount, float totalSavings, String hotelName, String hotelJsonString,
            String hotelRoomJsonString, String confirmationNumber) {
        this.bookingId = bookingId;
        this.hotelId = hotelId;
        this.hotelRoomId = hotelRoomId;
        this.noRooms = noRooms;
        this.guests = guests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookedOnDate = bookedOnDate;
        this.status = status;
        this.price = price;
        this.discount = discount;
        this.customerMobile = customerMobile;
        this.roomType = roomType;
        this.userName = userName;
        this.userEmail = userEmail;
        this.taxRateInPercent = taxRateInPercent;
        this.finalCharges = finalCharges;
        this.bonanzaDiscount = bonanzaDiscount;
        this.totalSavings = totalSavings;
    }
    
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(int hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }

    public int getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(int noRooms) {
        this.noRooms = noRooms;
    }

    public Set<Guest> getGuests() {
        return guests;
    }

    public void setGuests(Set<Guest> guests) {
        this.guests = guests;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getBookedOnDate() {
        return bookedOnDate;
    }

    public void setBookedOnDate(String bookedOnDate) {
        this.bookedOnDate = bookedOnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public float getTaxRateInPercent() {
        return taxRateInPercent;
    }

    public void setTaxRateInPercent(float taxRateInPercent) {
        this.taxRateInPercent = taxRateInPercent;
    }

    public float getFinalCharges() {
        return finalCharges;
    }

    public void setFinalCharges(float finalCharges) {
        this.finalCharges = finalCharges;
    }

    public float getBonanzaDiscount() {
        return bonanzaDiscount;
    }

    public void setBonanzaDiscount(float bonanzaDiscount) {
        this.bonanzaDiscount = bonanzaDiscount;
    }

    public float getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(float totalSavings) {
        this.totalSavings = totalSavings;
    }
    
    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", hotelId=" + hotelId + ", hotelRoomId=" + hotelRoomId
                + ", noRooms=" + noRooms + ", guests=" + guests + ", checkInDate=" + checkInDate + ", checkOutDate="
                + checkOutDate + ", bookedOnDate=" + bookedOnDate + ", status=" + status + ", price=" + price
                + ", discount=" + discount + ", customerMobile=" + customerMobile + ", roomType=" + roomType
                + ", userName=" + userName + ", userEmail=" + userEmail + ", taxRateInPercent=" + taxRateInPercent
                + ", finalCharges=" + finalCharges + ", bonanzaDiscount=" + bonanzaDiscount + ", totalSavings=" + totalSavings +  "]";
    }
    
}
