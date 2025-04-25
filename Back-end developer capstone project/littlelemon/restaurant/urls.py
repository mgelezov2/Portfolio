from django.contrib import admin 
from django.urls import path, include
from .views import MenuItemsView, SingleMenuItemView,TableListView, SingleTableView
from . import views

urlpatterns = [ 
    path('', views.index, name='index'), # Root URL
    path('menu/', MenuItemsView.as_view(), name='menu-items'),# Menu URL for menu list
    path('menu/<int:pk>/', SingleMenuItemView.as_view(), name='single-menu-item'),# Menu URL for Updating, get or deletion of an item
    path("tables/", TableListView.as_view(), name="table-list"), # Table URL to list all tables
    path("tables/<int:pk>/", SingleTableView.as_view(), name="single-table"), # Table URL for Updating, get or deletion of a table
]