# Generated by Django 5.1.5 on 2025-02-20 12:18

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('restaurant', '0002_rename_tablemodel_menuitem'),
    ]

    operations = [
        migrations.RenameModel(
            old_name='MenuItem',
            new_name='TableModel',
        ),
    ]
