from django.test import TestCase
from django.urls import reverse
from rest_framework.test import APIClient
from rest_framework import status
from restaurant.models import TableModel, BookingModel, MenuModel
from restaurant.serializers import MenuSerializer, BookingSerializer, TableSerializer
from django.contrib.auth.models import User
from django.utils.timezone import make_aware
from datetime import datetime

# Test to retrieve Menu Items
class MenuViewTest(TestCase):
    def setUp(self):
        self.user = User.objects.create_user(username="user_test", password="password_test")
        self.client = APIClient()
        self.client.force_authenticate(user=self.user)

        self.item1 = MenuModel.objects.create(title="Mead", price=10.00, inventory=3)
        self.item2 = MenuModel.objects.create(title="Roasted beef", price=35.00, inventory=7)

    def test_get_all_menu_items(self):
        response = self.client.get(reverse("menu-items"))
        expected_data = MenuSerializer([self.item1, self.item2], many=True).data

        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.json(), expected_data)
# Test to retrieve tables
class TableViewTest(TestCase):
    def setUp(self):
        self.user = User.objects.create_user(username="user_test", password="password_test")
        self.client = APIClient()
        self.client.force_authenticate(user=self.user)
        self.table1 = TableModel.objects.create(title="Table 1", price=20.00, inventory=4)
        self.table2 = TableModel.objects.create(title="Table 2", price=30.00, inventory=2)

    def test_get_all_tables(self):
        response = self.client.get(reverse("table-list"))
        expected_data = TableSerializer([self.table1, self.table2], many=True).data
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.json(), expected_data)

# Test to retrieve bookings
class BookingViewSetTest(TestCase):
    def setUp(self):
        self.user = User.objects.create_user(username="user_test", password="password_test")
        self.client = APIClient()
        self.client.force_authenticate(user=self.user)

        self.booking1 = BookingModel.objects.create(
            name="Divayth Fyr",
            no_of_guests=3,
            booking_date=make_aware(datetime(2025, 2, 20, 15, 30, 0))
        )
        self.booking2 = BookingModel.objects.create(
            name="Maiq the liar",
            no_of_guests=2,
            booking_date=make_aware(datetime(2025, 2, 21, 19, 0, 0))
        )

    def test_get_all_bookings(self):
        response = self.client.get(reverse("booking-list"))
        
        expected_data = BookingSerializer([self.booking1, self.booking2], many=True).data

        for item in expected_data:
            item["booking_date"] = item["booking_date"].replace(" ", "T")
            if not item["booking_date"].endswith("Z"):
                item["booking_date"] += "Z"

        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.json(), expected_data)