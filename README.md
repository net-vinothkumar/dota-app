bayes-dota
==========

This is the [task](TASK.md).

Any additional information about your solution goes here.

Thanks for the opportunity, it was really good to implement the
features.

Note : The solution is not complete yet.

I have created different parsers in the service package
to provide "SRP - Single Responsibility Principle"

The responsibility of the parser classes is to scan
the payload and create the corresponding Hero* objects
i.e HeroKills, HeroItems, etc.

I have used the spring data repository class to save the
data and retrieve the data.

Only Two API's implementation done so far.

Things to improve :
-------------------
1. Finish all the other API's
2. Refactor the MatchServiceImpl class
3. Look for duplicate code in all the Parser and
refactor appropriately
4. Consider the edge cases like hero name or item,
kills, damage information is not given.
5. API level testing using TestRestTemplate.
