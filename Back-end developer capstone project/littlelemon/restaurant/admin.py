from django.contrib import admin
from .models import BookingModel, TableModel, MenuModel
# Register your models here.
admin.site.register(BookingModel)
admin.site.register(TableModel)
admin.site.register(MenuModel)