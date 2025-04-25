from django.test import TestCase
from restaurant.models import BookingModel, TableModel, MenuModel

# String test representing the Booking model
class BookingModelTest(TestCase):
    def test_booking_str(self):
        booking = BookingModel.objects.create(
            name="John Philips",
            no_of_guests=3,
            booking_date="2025-02-20 20:30:00"
        )
        self.assertEqual(str(booking), "Booking: John Philips, Guests: 3, Date: 2025-02-20 20:30:00")

#String test representing the Table model
class TableModelTest(TestCase):
    def test_table_str(self):
        table = TableModel.objects.create(
            title="Table 2",
            price=13.99,
            inventory=2
        )
        self.assertEqual(str(table), "Table 2 - $13.99 (Inventory: 2)")

#String test representing the Menu model
class MenuModelTest(TestCase):
    def test_menu_str(self):
        menu_item = MenuModel.objects.create(
            title="Sweetroll", 
            price=12.99, 
            inventory=30
            )
        self.assertEqual(str(menu_item), "Sweetroll - $12.99 (Inventory: 30)")