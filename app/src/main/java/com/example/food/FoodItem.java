package com.example.food;

import java.io.Serializable;

public class FoodItem implements Serializable {

        private long eventId;
        private long foodItemId;
        private String description;
        private String date;
        private String location;
        private String availableUntil;
        private String foodName;
        private String foodDescription;
        private String quantity;
        private String allergen;
        private boolean claimed;

        public long getEventId() {
            return eventId;
        }

        public void setEventId(long eventId) {
            this.eventId = eventId;
        }

        public long getFoodItemId() {
            return foodItemId;
        }

        public void setFoodItemId(long foodItemId) {
            this.foodItemId = foodItemId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getAvailableUntil() {
            return availableUntil;
        }

        public void setAvailableUntil(String availableUntil) {
            this.availableUntil = availableUntil;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public String getFoodDescription() {
            return foodDescription;
        }

        public void setFoodDescription(String foodDescription) {
            this.foodDescription = foodDescription;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getAllergen() {
            return allergen;
        }

        public void setAllergen(String allergen) {
            this.allergen = allergen;
        }

    // Getter and setter for the claimed field
    @Override
    public String toString() {
        return "Description: " + description +
                "\nDate: " + date +
                "\nLocation: " + location +
                "\nAvailable Until: " + availableUntil +
                "\nFood Name: " + foodName +
                "\nFood Description: " + foodDescription +
                "\nQuantity: " + quantity +
                "\nAllergen: " + allergen;
    }
}
