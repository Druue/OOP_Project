# OOPP Week 6 meeting
Date: 16/03/2020\
Note Taker: Justin Jo

## Schema
- Make sure everyone knows what you are doing
- Changing entities etc without discussing and taking over others work doesn't speed up the whole project but only slows it down

## Merging branches
- Before working on any additional features, make sure our individual codes work together

## Code quality
- Checkstyle errors on frontend
- Before you push, make sure everything is up to checkstyle standard
- import checksyle issues: ctrl + alt (+ shift) + L

## Deadlines - week 6
- Reservations working
- Searching rooms
- Filtering
- Bike (higher grade)

## Nikko's chaotic notes
Entities conflicts. Dimitar's commit, a ton of entities and schema changes not discussed.
This morning Sophie and Thomas spent hours fixing it
Dixit: Decide on a schema. If something everybody agrees on something, implement it. If there is
something that someone thinks would improve it, discuss it with the group. If everybody
agrees, implement it. Discuss it well first

_Everybody should know how the application works in general. Not every line of code, but each general
part.It would help with the presentation as well_

Sophie: Setup a Docker guide. Sorting out the schema and database stuff with Mylene
Mylene: Getting the database to run (with the old schema) on Thursday on Nikko's and Thomas' laptops. Fixing entities
and schema logic
Ayham: Fixed some things on a single branch, but the entities he fixed were connected and would create conflicts. There are a
TON of conflicts. Working on filtering. Everything is on local machine

#### TODO:Discuss how to change the entities so that filtering wouldn't break anything.
We should take some time to merge and make sure everything works well together so far.
Sophie fixed all checkstyle errors in merge/week5
When Sophie pulls most code, she gets a ton of Checkstyle errors, "Did you mean that instead" errors and so on...
When committing, make sure to AutoFormat.

_Make sure to name your variables well, because that could create a lot of conflicts if renamed in the future._
#### TODO: Turn on AutoFormat on Save. And change what AutoFormat does - does it change only your code, all, does it rearrange, etc...
Get everything you have right now work well together, then work from there.

Q:"Communication problems might continue this way..."
A:"Dixit will talk to Yoshi and will reply by tomorrow. D will reply ASAP"