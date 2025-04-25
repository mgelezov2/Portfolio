from django.contrib import admin 
from django.urls import path, include
from rest_framework.routers import DefaultRouter
from django.contrib import admin 
from django.urls import path, include
from rest_framework.routers import DefaultRouter
from restaurant.views import BookingViewSet
from rest_framework.authtoken.views import obtain_auth_token
 
#Booking API registered as a viewset globally
router = DefaultRouter()
router.register(r'booking', BookingViewSet, basename="booking")

urlpatterns = [ 
    path('admin/', admin.site.urls), # Admin panel
    path('restaurant/', include('restaurant.urls')), # Restaurant app URLS
    path('restaurant/', include(router.urls)), # Registered booking endpoints
    path('auth/', include('djoser.urls')), # User authentication endpoints
    path('auth/', include('djoser.urls.authtoken')), # Token authentication
    path('api-token-auth/', obtain_auth_token, name='api_token_auth'), # Token authentication endpoint
]
