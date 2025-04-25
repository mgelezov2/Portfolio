from rest_framework import serializers
from .models import TableModel, BookingModel, MenuModel
# Table serializer for converting objects from table model to JSON
class TableSerializer(serializers.ModelSerializer):
    class Meta:
        model = TableModel
        fields = '__all__'
# Booking serializer for converting objects from booking model to JSON
class BookingSerializer(serializers.ModelSerializer):
    class Meta:
        model = BookingModel
        fields = '__all__'
# Menu serializer for converting objects from menu model to JSON
class MenuSerializer(serializers.ModelSerializer):
    class Meta:
        model = MenuModel
        fields = '__all__'