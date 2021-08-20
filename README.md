# stacks_queues_example

Made this for a Data Structures class assignment - Stack and Queues week
See input and output folders for examples of format of data in text file.
I got an A.

Right now reads files hardcoded "input/assn4input1.txt" and "input/carInfo1d.txt" in CS310Kim.java processFile() and processCarInfo(), respectively. Maybe I'll eventually update to prompt user.

(Not intended for cheating!)
Excerpt from the assignment:
"The real estate office is located in the middle of [city X], where parking is scarce. Therefore, the office maintains some cars for the realtors to use. Due to limited space in the city, parking for the cars is only available one narrow lot that will hold two lines of parked cars.
Small basic cars are parked in one single line and larger luxury cars are parked in a second single line. There is only one entrance/exit for each line, at the front of lot. Cars are numbered, starting from 1 in the basic line, and continuing the numbering in the luxury line. The lines can currently hold 4 basic cars and 3 luxury cars.
So the basic cars will be numbered 1 to 4, and the luxury cars will be numbered 5 to 7.

-----------------------------------------------
| BasicCar1 BasicCar2 BasicCar3 BasicCar4
-----------------------------------------------
| LuxuryCar5 LuxuryCar6 LuxuryCar7
-----------------------------------------------

This program will use stacks and queues to control use of vehicles in the lots.
As a reward for top sellers, realtors with over \$1,000,000 in property listings have access to the luxury cars. When a realtor requests a car, they will be assigned the first available car from the appropriate line. A top seller is assigned cars from the luxury car line, and everyone else gets cars from the basic car line.
If all cars from the luxury line are gone, a top seller may be assigned a car from the basic car line. If all cars from both lines in the lot are out, or the realtor is not a top seller and the basic car line is empty, the realtor must wait until a car is returned. The requesting realtor is placed into a queue. There will be a queue for top sellers, and a queue for the other realtors. Realtors in the top seller queue will be assigned any returned cars before the realtors in the other queue.
