from django.shortcuts import render
from rest_framework.generics import ListCreateAPIView, RetrieveUpdateDestroyAPIView
from rest_framework.viewsets import ModelViewSet
from .models import TableModel, BookingModel, MenuModel
from .serializers import MenuSerializer, BookingSerializer,TableSerializer
from rest_framework.permissions import IsAuthenticated

# index view used for html homepage rendering
def index(request):
    return render(request, 'index.html', {})
# Menu items view  used for menu related requests such as GET requests and POST requests
class MenuItemsView(ListCreateAPIView):
    queryset = MenuModel.objects.all()
    serializer_class = MenuSerializer
# Menu items view used for retrieving updates and deletions of items
class SingleMenuItemView(RetrieveUpdateDestroyAPIView):
    queryset = MenuModel.objects.all()
    serializer_class = MenuSerializer
# Booking view  used for booking related requests such as GET requests and POST requests
# Requires authetication for viewing
class BookingViewSet(ModelViewSet):
    queryset = BookingModel.objects.all()
    serializer_class = BookingSerializer
    permission_classes = [IsAuthenticated]
# Table view  used for table related requests such as GET requests and POST requests
class TableListView(ListCreateAPIView):
    queryset = TableModel.objects.all()
    serializer_class = TableSerializer
# Table view used for retrieving updates and deletions of items
class SingleTableView(RetrieveUpdateDestroyAPIView):
    queryset = TableModel.objects.all()
    serializer_class = TableSerializer