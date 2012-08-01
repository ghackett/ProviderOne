This project is still in a very volatile stage of development. While it may be mostly functional, it is still subject to rapid changes with no consideration for backwards compatibility (this will not always be the case). If you want to use this project or the code it generates in a production application, it is HIGHLY recommended that you fork it first.

It's also worth noting that I'm an Android developer and this is my first ruby/rails app. So if you notice that I'm doing something really, terribly wrong in the ruby code, please let me know.


ProviderOne
-----------

Take the pain out of dealing with databases in Android by generating fully functional database, content provider, and ORM object code, from nothing more than a pre-built sqlite database or a .sql file.

## What's supported?

- Tables
- Indices
- Views
- Loaders (NEW!)
- Table & View auto-updating (both column adds and removals (NEW!) are supported but be careful when changing types and constraints)

## How does it work?

1) Build your database in your favorite sqlite editor (make sure every table has an auto-incrementing primary key called _id).
	
  --you can also use a .sql text file which is more git-friendly. 
	
2) Upload that database file to the ProviderOne web project

3) Answer a few simple questions about your project and database (you can pick a 2nd lookup key besides _id for each table or view)

4) Download all your generated Android code and copy it into your project, saving you hours (possibly days) of tedious database work. 

  --if updating your schema (as opposed to generating a brand new one) you should only copy the autogen folder into your project to avoid overwriting any of your custom subclasses.

IT'S JUST THAT EASY!


## What's included in the download?

- A SQLiteOpenHelper that handled creating and upgrading the database automatically
- A ContentProvider base class that handles lookups generically, by _id and by lookup key (as well as count queries)
- An empty ContentProvider subclass so you can add your own Uri bindings to the provider
- A TableInfo class (autogen.tables pkg) for each table/view that contains the column and uri definitions for the table (think of it as one of the subclasses in a Contract, only separated out into individual files for easier management and readability).
- An empty TableInfo subclass (tables pkg) for each table/view so you can add your own Uri (and possibly column) definitions.
- An ORM object class for each table/view (autogen.objects pkg) and empty subclass (objects pkg) that make data retrieval a breeze.
- An abstract list adapter for each table/view (relies on the android support package) so you don't even have to see the word Cursor if you don't want to.
- An AsyncTaskLoader (also relies on android support package) for each table/view to make it easy for your fragments to load a single ORM object

## But aren't ORM objects notoriously slow in Android? Especially when used in lists?

Not anymore. Most ORM objects force you to fetch and load every column in a table, and when they hydrate their objects, they tend to do so by name, which can slow things down significantly when used in lists. Most also use some form of reflection to figure out what the database should look like and how to hydrate the object. Since all the ProviderOne code is generated for you based on your database schema, we never have to use reflection, and you don't have to write a single line of db code. Also, ProviderOne objects only load the columns you want, and never get those columns by name. Instead we use something called a ColumnHelper (defined in the TableInfo classes for each table). ColumnHelpers are simple objects that contain a single int value representing the order for each column in your table (based on your projection) or -1 if the column was not included in your projection. While this may introduce a negligible performance hit when loading a single record, when used in a list it results in a big benefit from not looking up up each column by name, and instead already knowing the order of the columns in your projection.

## Database rules

There are a few rules that your database must abide by in order to get properly generated java files…

- Every table MUST contain a primary, auto-incrementing key called _id. If you've used android cursor adapters before, then you should be familiar with this design pattern.
- Views are supported exactly like Tables (except with some sanity checking so you can't edit view records). This means that every view must still contain an _id column, just like tables. If you don't have an _id to select from, you should be able to include a `SELECT 0 AS '_id'` style hack to fake an _id column in your CREATE VIEW statement.
- All sqlite column names must be lowercase and underscored, i.e. my_column_name. In java, you would call getMyColumnName() to fetch the value of that column, or isMyColumnNameSet() to find out if that column is current loaded in that object.

## Supported Column Types

ProviderOne supports a number of standard and non-standard sqlite column types. While the sqlite doesn't care about columns types, java does. So these types are used to determine what type of object the column should map to. You'll notice that all of the java types are Objects instead of primitives, so that they can also be set to null. Below is a list of the supported column types and the java types they map to.

- INT, INTEGER => Integer
- TEXT, STRING, VARCHAR, CLOB => String
- DOUBLE, REAL, NUMERIC => Double
- FLOAT => Float
- LONG => Long
- BOOL, BOOLEAN => Boolean
- CHAR, CHARACTER => Character
- BLOB => ByteBuffer
- DATETIME, DATE, TIMESTAMP => Long (time in millis, with helper methods to convert to and from Date objects)

It's also worth noting that the `_id` column of every table/view will be treated as a Long, regardless of it's sqlite type.

## Running the Rails app

This project was built using ruby v1.9.3-rc1 and rails v3.1.0. If you have both of those installed, you should be able to start the rails app pretty quickly. In your terminal, navigate to the project folder/ruby/ProviderOne, type `bundle install` then `rails server`. Once the server is running, point your browser to http://localhost:3000 and you should be good to go.

## License

(The MIT-License)

Copyright (c) 2011 GroupMe

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 

