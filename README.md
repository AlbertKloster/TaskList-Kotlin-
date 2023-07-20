# Stage 2/6: The tasklist menu
## Description
In this stage, we will implement a new menu that will prompt users to either enter a task or print all tasks. Since each task is added individually, it is possible to enter a multiple-line task.

Each task input should end when users input a blank line (or just spaces and tabs).

## Objectives
Change the welcoming line to `Input an action (add, print, end):`. If users input:
1. Anything else than `add`, `print`, or `end` (case-insensitive), print `The input action is invalid` and ask again for the input;
2. `End` — print `Tasklist exiting!` and terminate;
3. `Add` — ask users to add a task with the `Input a new task (enter a blank line to end):` line. Input is terminated by a blank line. When a task is added, remove any leading or trailing spaces or tabs in the tasks;
4. `Print` — print the tasks as explained below. If there're no tasks, print `No tasks have been input`.

If a blank line (or some spaces and tabs) is input as a task, print `The task is blank`. Don't save this task. The tasks should be printed in the following format:
```
1  Task1-line1
   Task1-line2
   Task1-line3

2  Task2-line1

3  Task3-line1

4  Task4-line1
   Task4-line2

5  Task5-line1

6  Task6-line1

7  Task7-line1
   Task7-line2
   Task7-line3

8  Task8-line1

9  Task9-line1

10 Task10-line1

11 Task11-line1
```

The first line of each task is prefixed by a sequence number (starting from `1`).

Each task line starts at the 4<sup>th</sup> column.

Divide the tasks with a blank line.

## Examples
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

<b>Example 1:</b> <i>normal execution</i>
```
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> Supermarket
> -----------
> butter
> milk
> meat
>
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> See my dentist on 14/1/22
>
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> Check my report
> a) Grammar errors
> b) Verify numbers
> c) Check citations
>
Input an action (add, print, end):
> print
1  Supermarket
   -----------
   butter
   milk
   meat

2  See my dentist on 14/1/22

3  Check my report
   a) Grammar errors
   b) Verify numbers
   c) Check citations

Input an action (add, print, end):
> end
Tasklist exiting!
```

<b>Example 2:</b> <i>normal execution with 10 or more tasks</i>
```
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> See my dentist on 14/1/22
>
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> Supermarket
> Chocolates, flour, oranges
>
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> Buy book
>
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> Change colors at site
> Use Christmas theme
>
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> Pay phone bill
>
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> Pay water bill
>
Input an action (add, print, end):
add
Input a new task (enter a blank line to end):
> Fix my printer
>
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> Dentist on 15/1
>
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> Cinema: get tickets
> Check movie reviews
>
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> Present for friend birthday
>
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> Check new software
>
Input an action (add, print, end):
> print
1  See my dentist on 14/1/22

2  Supermarket
   Chocolates, flour, oranges

3  Buy book

4  Change colors at site
   Use Christmas theme

5  Pay phone bill

6  Pay water bill

7  Fix my printer

8  Dentist on 15/1

9  Cinema: get tickets
   Check movie reviews

10 Present for friend birthday

11 Check new software

Input an action (add, print, end):
> end
Tasklist exiting!
```

<b>Example 3:</b> <i>no tasks</i>
```
Input an action (add, print, end):
> print
No tasks have been input
Input an action (add, print, end):
> end
Tasklist exiting!
```

<b>Example 4:</b> <i>a blank task</i>
```
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
>
The task is blank
Input an action (add, print, end):
> print
No tasks have been input
Input an action (add, print, end):
> end
Tasklist exiting!
```

<b>Example 5:</b> <i>unnecessary parts removed</i>
```
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
> Supermarket
> 		butter
> 	  milk
>    flour
>     chocolates
>
Input an action (add, print, end):
> add
Input a new task (enter a blank line to end):
>    Fix my printer
>
Input an action (add, print, end):
> print
1  Supermarket
   butter
   milk
   flour
   chocolates

2  Fix my printer

Input an action (add, print, end):
> end
Tasklist exiting!
```

<b>Example 6:</b> <i>Invalid actions</i>
```
Input an action (add, print, end):
> task
The input action is invalid
Input an action (add, print, end):
> input
The input action is invalid
Input an action (add, print, end):
> end
Tasklist exiting!
```
