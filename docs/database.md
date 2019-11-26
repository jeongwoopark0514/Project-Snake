### Database utilization

We need to ensure that the database is always in a consistent state, 
changes of person 1 don't get overwritten by changes from person 2
and all changes are reversible and reviewable.
To ensure this we need to have some basic guidelines.

When your new feature or change requires a change in the database,
 please follow the following steps:
 
 * Create a local database, or use a existing one. 
 You can use the existing `setup.sql` file (in the root folder of the project) to bring your local database to the same version as the release database (EWI Project Server Database)
 * Change the configuration in the project source code to use this local database instead of the release database.
 * Make your required changes to your own local database,
 also put these changes into the `setup.sql` file.
 * Continue your usual development cycle, test, execute, test etc.
 * When done make sure all your changes to the database are in the `setup.sql` file
 and before creating a merge request, make sure you change the database configuration back.
 When your feature is merged, your code will become release code,
 which should also use the release database.
 
 If everyone follows these guidelines this well ensure the following:
 * Every change you make is persistent:
    - Imagine everyone just used the release database:
    if person 1 made a change to the release database,
    he/she assumes that the change is persistent. 
    Then person 2 also makes a change to the release database,
    potentially overriding the changes person 1 made.
    The problem that arises is that different people need to work with different versions,
    but there is only one database that can provide one version.
    
    - By using local databases instead, all changes you make are persistent for you.
    Since your development code uses this local database,
    there is no external force that can make a change and everything is up to you.
 * All changes are reviewable and reversible:
    - All changes are put in the `setup.sql` file,
    meaning when you needed a new table you put `create table .. (..);` into the setup file.
    In this way all changes will also be visible in the Git system,
    which gives access to reviewable and reversible code.
 * The release database is automatically compatible with the newest version:
    - When a change to the database is merged with the master branch, 
    the CI/CD will ensure the new setup file is run on the release database,
    ensuring that the version that actually runs on the database is compatible with the latest release code.