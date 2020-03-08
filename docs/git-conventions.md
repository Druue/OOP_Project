### Naming a branch
Do: `feat/x-name`, `feat/backend-db_conn`, `feat/frontend-loginbutton`

Don't: `patch-1`, `stuff`, `person-test`

### Merging
Start a merge request with WIP when it ***shouldn't*** be merged yet.
Consequently, when ready to merge, remove the WIP prefix. 
All merge requests not marked WIP are to be reviewed and merged ASAP.

When merging branches, create a separate branch named e.g. `merge/frontend-example1-and-2`

### Referencing things within issues/commits

``` 
#id // Reference to an issue
!id // Reference to a merge request
%name // Reference to a milestone
@name // Reference to a person
``` 

### Other resources

https://chris.beams.io/posts/git-commit/

https://github.com/agis/git-style-guide