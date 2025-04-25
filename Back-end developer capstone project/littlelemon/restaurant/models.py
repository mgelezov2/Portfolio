from django.db import models

# Booking model for bookings
class BookingModel(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=255)
    no_of_guests = models.PositiveIntegerField()
    booking_date = models.DateTimeField()

    def __str__(self):
        return f'Booking: {self.name}, Guests: {self.no_of_guests}, Date: {self.booking_date}'
# Table model for tables at a restaurant
class TableModel(models.Model):
    id = models.AutoField(primary_key=True)
    title = models.CharField(max_length=255)
    price = models.DecimalField(max_digits=10, decimal_places=2)
    inventory = models.PositiveIntegerField()

    def __str__(self):
        return f'{self.title} - ${self.price} (Inventory: {self.inventory})'
# Menu model for menu items
class MenuModel(models.Model):
    id = models.AutoField(primary_key=True)
    title = models.CharField(max_length=255)
    price = models.DecimalField(max_digits=10, decimal_places=2)
    inventory = models.PositiveIntegerField()

    def __str__(self):
        return f'{self.title} - ${self.price} (Inventory: {self.inventory})'


