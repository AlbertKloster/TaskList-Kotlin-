# Stage 1/6: Read the list
## Description
Applications that keep track of our tasks are very practical and handy. In this project, we're going to create one such tool.

We will start with little things. Your program should obtain the tasks from the input, one per line, and then print them. The tasks should contain an index number.

## Objectives
- Ask for input, by printing `Input the tasks (enter a blank line to end):`. Read each task, one per line. Get rid of any leading or trailing spaces and tabs;
- The tasks input should end when the user inputs a blank line. That is, a line without any text. If a user inputs a line that contains only spaces and tabs, consider it as a blank line.
- Print the tasks in the following format:
```
1  Task1
2  Task2
3  Task3
4  Task4
5  Task5
6  Task6
7  Task7
8  Task8
9  Task9
10 Task10
11 Task11
```
- The listing of tasks starts with `1`. The first nine tasks contain 2 spaces between the number and the task. Starting from task 10, leave only one space (see the Examples section);
- If users don't input any tasks (a blank line), print `No tasks have been input`.

## Examples
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

<b>Example 1:</b> <i>normal execution</i>
```
Input the tasks (enter a blank line to end):
> Dentist on 15/1
> Present for friend birthday
> Supermarket. Milk, eggs, butter.
>
1  Dentist on 15/1
2  Present for friend birtday
3  Supermarket. Milk, eggs, butter.
```

<b>Example 2:</b> <i>normal execution with 10 or more tasks</i>
```
Input the tasks (enter a blank line to end):
> Dentist on 15/1
> Present for friend birthday
> Supermarket. Milk, eggs, butter.
> Cinema: get tickets
> Buy book
> Check new software
> Finish hyperskill project
> Change colors at site
> Pay phone bill
> Pay water bill
> Fix the printer.
>
1  Dentist on 15/1
2  Present for friend birthday
3  Supermarket. Milk, eggs, butter.
4  Cinema: get tickets
5  Buy book
6  Check new software
7  Finish hyperskill project
8  Change colors at site
9  Pay phone bill
10 Pay water bill
11 Fix the printer.
```

<b>Example 3:</b> <i>extra parts are removed</i>
```
Input the tasks (enter a blank line to end):
>         Dentist on 15/1
>    Buy book
>     Change colors at site
>
1  Dentist on 15/1
2  Buy book
3  Change colors at site
```

<b>Example 4:</b> <i>no input</i>
```
Input the tasks (enter a blank line to end):
>
No tasks have been input
```
