package com.example.cinebilet;



public abstract class Ticket {
    protected int pricePerSeat;

    public abstract int calculateTotalPrice(int numberOfSeats);

    public int getPricePerSeat() {
        return pricePerSeat;
    }
}

class StudentTicket extends Ticket {
    public StudentTicket() {
        pricePerSeat = (int) (40 * 0.75);  // Tam biletin %25 indirimiyle öğrenci bilet fiyatı
    }

    @Override
    public int calculateTotalPrice(int numberOfSeats) {
        return pricePerSeat * numberOfSeats;
    }
}


class FullTicket extends Ticket {
    public FullTicket() {
        pricePerSeat = 40;  // Tam bilet fiyatı
    }

    @Override
    public int calculateTotalPrice(int numberOfSeats) {
        return pricePerSeat * numberOfSeats;
    }
}
