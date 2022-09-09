Assumptions:
- Rectangles are always straight up and down, i.e. the slope of the top and bottom lines
are 0 and slope of the sides are infinite
- dimensions of rectangle can be partial numbers (assumed doubles)
- case where rectangles touch at one corner is considered adjacent (separate and intersection don't seem right for this)

No noteworthy external libraries were used - the only data structures used that weren't built
from scratch were HashMap and ArrayList.

The Rectangle class has a generic external facing method overlap() that determines
how a Rectangle r overlaps with the Rectangle object on which the method is invoked. 
This overlap() method has helper methods that determine if there is a containment, adjacency, 
or intersection scenario. The return value takes the form of a HashMap with the key being the type of
overlap situation and the value being null if not an intersection, otherwise a list of Points
where the intersection happens is returned.

Due to travel and work constraints I did not expand on the problem, though desired expansions
would include support for rectangles that are not straight up and down and some
form of output that draws the rectangles so it's easy to visualize the situation.