This project is still in a very volatile stage of development. While it may be mostly functional, it is still subject to rapid changes with no consideration for backwards compatibility (this will not always be the case). If you want to use this project or the code it generates in a production application, it is HIGHLY recommended that you fork it first.


ProviderOne
-----------

Take the pain out of dealing with databases in Android by generating fully functional database, content provider, and ORM object code, from nothing more than a pre-built sqlite database or a .sql file.


## How it works

1) Build your database in your favorite sqlite editor (make sure every table has an auto-incrementing primary key called _id).
	
	-you can also use a .sql text file which is more git-friendly. 
2) Upload that database file to the ProviderOne web project

3) Answer a few simple questions about your project and database (you can pick a 2nd lookup key besides _id for each)

4) Download all your generated Android code and copy it into your project, saving you hours (possibly days) of tedious database work. 

	-if updating your schema (as opposed to generating a brand new one) you should only copy the autogen folder into your project to avoid overwriting any of your custom subclasses.

IT's JUST THAT EASY!


## What's included in the download

- A SQLiteOpenHelper that handled creating and upgrading the database automatically
- A ContentProvider base class that handles lookups generically, by _id and by lookup key (as well as count queries)
- An empty ContentProvider subclass so you can add your own Uri bindings to the provider
- A TableInfo class (autogen.tables pkg) for each table that contains the column and uri definitions for the table (think of it as one of the subclasses in a Contract, only separated out into individual files for easier management and readability).
- An empty TableInfo subclass (tables pkg) for each table so you can add your own Uri (and possibly column) definitions.
- An ORM object class for each table (autogen.objects pkg) and empty subclass (objects pkg) that make data retrieval a breeze.
- An abstract list adapter for each table (relies on the android support package) so you don't even have to see the word Cursor if you don't want to.

## But aren't ORM objects notoriously slow in Android? Especially when used in lists?

Not anymore. Most ORM objects force you to fetch and load every column in a table, and when they hydrate their objects, they tend to do so by name, which can slow things down significantly when used in lists. ProviderOne objects only load the columns you want, and never get those columns by name. Instead we use something called a ColumnHelper (defined in the TableInfo classes for each table). ColumnHelpers are simple objects that contain a single int value for each column in your table (based on your projection) or -1 if the column was not included in your projection. While this may introduce a negligible performance hit when loading a single record, when used in a list it results in a big benefit from not looking up up each column by name, and instead already knowing the order of the columns in your projection.

## Database rules

Because ProviderOne is still in the early stages of development, there are a few rules that your database must abide by in order to get properly generated java files…

-Every table MUST contain a primary, auto-incrementing key called _id. If you've used android cursor adapters before, then you should be familiar with this design pattern.
-No Views, Provider one currently does not support SQL Views. View support may be added in the future, but as of now, they will just be ignored and will not be created on the device.
-All sqlite column names should be lowercase and underscored, i.e. my_column_name. In java, you would call getMyColumnName() to fetch the value of that column, or isMyColumnNameSet() to find out if that column is current loaded in that object.

