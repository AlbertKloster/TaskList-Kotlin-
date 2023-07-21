# Stage 4/6: Edit the list
## Description
In this stage, we will implement the ability to delete or edit a task. In addition, we will also add a tag that signifies whether a task date is due or past.

The process of removing a task is based on the tasks sequence numbers. After deletion, the sequence numbers change.

Editing a task requires the change of priority, date, time, or task description. Due to IntelliJ run terminal limitations, editing can not be done over the data from the old task. That is, printing the old data and then editing them is not possible. So, for each of the fields above, you need to make another one and replace the old.

A due tag will point out whether a task is overdue or not. This is just one letter — `I`, `T`, or `O` (case-insensitive). They stand for In time, Today, and Overdue. To define the due tag, we will compare the current day with the task date.

Following is an example of how to calculate the days from the current date until a certain date using the `daysUntil` function from the KotlinX `datetime` that applies to a `LocalDate` instance:
```kotlin
val taskDate = LocalDate(2022, 1, 9)
val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0")).date
val numberOfDays = currentDate.daysUntil(taskDate)
```

The `daysUntil` function returns:

- `0` if the `taskDate` is the same as the `currentDate` (due tag `T`)
- a positive integer, if the `taskDate` is past the `currentDate` (due tag `I`)
- a negative integer, if the `taskDate` is before the `currentDate` (due tag `O`)

## Objectives
Change the introduction line to `Input an action (add, print, edit, delete, end):`.

If users inputs `delete`, print the task list in the new format (see below) and then:
- If there are no tasks, print the message: `No tasks have been input`;
- Otherwise, ask users for a task number with the `Input the task number (1-<Maximum task number>):` message; if the task number is outside the range or it isn't a valid natural number, print `Invalid task number` and ask again for the task number;
- When the task is deleted, print `The task is deleted`.

If users inputs `edit`, print the task list in the new format (see below) and then:
- If there are no tasks, print `No tasks have been input`;
- Otherwise, ask users for a task number with the `Input the task number (1-<Maximum task number>):` message; if the task number is outside the range or it isn't a valid natural number, print `Invalid task number` and ask again for the task number;
- Ask users which part of a task should be edited with the `Input a field to edit (priority, date, time, task):` message; Users should provide one of `priority`, `date`, `time`, `task`. If anything else, print `Invalid field` and ask for a field once again;
- When users input a valid field, ask for data in that field in the same way as in the previous stage; after success, print `The task is changed`

If users input `print`, print the task list in the new format that includes the due tag at the end of the first line of each task. The due tag is separated by a single space. The new format is provided below:
```
1  Date1 Time1 Priority1 DueTag1
Task1-line1
Task1-line2

2  Date2 Time2 Priority2 DueTag2
Task2-line1
```

## Examples
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

<b>Example 1:</b> <i>normal execution (current day — 2022-1-8)</i>
```
Input an action (add, print, edit, delete, end):
> add
Input the task priority (C, H, N, L):
> h
Input the date (yyyy-mm-dd):
> 2021-12-25
Input the time (hh:mm):
> 14:00
Input a new task (enter a blank line to end):
> Christmas meal
>
Input an action (add, print, edit, delete, end):
> add
Input the task priority (C, H, N, L):
> N
Input the date (yyyy-mm-dd):
> 2022-1-8
Input the time (hh:mm):
> 19:15
Input a new task (enter a blank line to end):
> Dentist
>
Input an action (add, print, edit, delete, end):
> add
Input the task priority (C, H, N, L):
> L
Input the date (yyyy-mm-dd):
> 2022-1-10
Input the time (hh:mm):
> 19:00
Input a new task (enter a blank line to end):
> Supermarket
> -----------
> Pasta
> Butter
> Cheese
>
Input an action (add, print, edit, delete, end):
> print
1  2021-12-25 14:00 H O
   Christmas meal

2  2022-01-08 19:15 N T
   Dentist

3  2022-01-10 19:00 L I
   Supermarket
   -----------
   Pasta
   Butter
   Cheese

Input an action (add, print, edit, delete, end):
> end
Tasklist exiting!
```

<b>Example 2:</b> <i>deleting a task</i>
```
Input an action (add, print, edit, delete, end):
> add
Input the task priority (C, H, N, L):
> n
Input the date (yyyy-mm-dd):
> 2021-12-25
Input the time (hh:mm):
> 14:00
Input a new task (enter a blank line to end):
> Christmas meal
>
Input an action (add, print, edit, delete, end):
> add
Input the task priority (C, H, N, L):
> L
Input the date (yyyy-mm-dd):
> 2022-1-8
Input the time (hh:mm):
> 19:15
Input a new task (enter a blank line to end):
> Dentist
>
Input an action (add, print, edit, delete, end):
> print
1  2021-12-25 14:00 N O
   Christmas meal

2  2022-01-08 19:15 L T
   Dentist

Input an action (add, print, edit, delete, end):
> delete
1  2021-12-25 14:00 N O
   Christmas meal

2  2022-01-08 19:15 L T
   Dentist

Input the task number (1-2):
> 3
Invalid task number
Input the task number (1-2):
> 1
The task is deleted
Input an action (add, print, edit, delete, end):
> print
1  2022-01-08 19:15 L T
   Dentist

Input an action (add, print, edit, delete, end):
> end
Tasklist exiting!
```

<b>Example 3:</b> <i>editing task fields</i>
```
Input an action (add, print, edit, delete, end):
> add
Input the task priority (C, H, N, L):
> N
Input the date (yyyy-mm-dd):
> 2022-1-7
Input the time (hh:mm):
> 15:00
Input a new task (enter a blank line to end):
> Supermarket
>
Input an action (add, print, edit, delete, end):
> edit
1  2022-01-07 15:00 N O
   Supermarket

Input the task number (1-1):
> 2
Invalid task number
Input the task number (1-1):
> 1
Input a field to edit (priority, date, time, task):
> tag
Invalid field
Input a field to edit (priority, date, time, task):
> field
Invalid field
Input a field to edit (priority, date, time, task):
> priority
Input the task priority (C, H, N, L):
> L
The task is changed
Input an action (add, print, edit, delete, end):
> edit
1  2022-01-07 15:00 L O
   Supermarket

Input the task number (1-1):
> 1
Input a field to edit (priority, date, time, task):
> date
Input the date (yyyy-mm-dd):
> 2022-1-11
The task is changed
Input an action (add, print, edit, delete, end):
> edit
1  2022-01-11 15:00 L I
   Supermarket

Input the task number (1-1):
> 1
Input a field to edit (priority, date, time, task):
> time
Input the time (hh:mm):
> 19:00
The task is changed
Input an action (add, print, edit, delete, end):
> edit
1  2022-01-11 19:00 L I
   Supermarket

Input the task number (1-1):
> 1
Input a field to edit (priority, date, time, task):
> task
Input a new task (enter a blank line to end):
> Supermarket: Salt, milk
>
The task is changed
Input an action (add, print, edit, delete, end):
> print
1  2022-01-11 19:00 L I
   Supermarket: Salt, milk

Input an action (add, print, edit, delete, end):
> end
Tasklist exiting!
```

<b>Example 4:</b> <i>no tasks</i>
```
Input an action (add, print, edit, delete, end):
> print
No tasks have been input
Input an action (add, print, edit, delete, end):
> edit
No tasks have been input
Input an action (add, print, edit, delete, end):
> delete
No tasks have been input
Input an action (add, print, edit, delete, end):
> end
Tasklist exiting!
```
