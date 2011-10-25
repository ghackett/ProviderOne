## ProverOne Package Readme

## First Time Users
- Copy the entire java/database folder into your android project folder in the /src/{PackageNameFolder}/ folder
- Add the following line to your AndroidManifest.xml file
	<provider android:authorities={ContentAuthority} android:name=".database.{ProjectName}Provider"></provider>
- If you want your database to remain private (i.e. you don't want other apps to be able to access it) add 
	exported="false"
- Save the config/ProviderOneConfig.xml file (along with either your binary database or original .sql text file) somewhere with your project. This is so you can use ProviderOne to easily update your database schema when needed, without having to declare your lookup keys and app/db info again.
- To customize you project, only modify the empty subclasses, do not modify anything in the autogen package, as those files will be overwritten whenever you update the schema.

## Returning users, updating a database schema
- As long as you have never modified anything in the autogen package it should be safe to replace your old /src/{PackageNameFolder}/database/autogen folder with the one included in this package.
- If you haven't added any new tables in this version, then you're done. Otherwise...
- Copy only the new Object and TableInfo files from the java/database/objects and java/database/tables folders in this package to /src/{PackageNameFolder}/database/objects and /src/{PackageNameFolder}/database/tables respectively. Be careful not to overwrite any of your old files in those folders if you've modified them.



## The following objects are now at your disposal
{ObjectList}