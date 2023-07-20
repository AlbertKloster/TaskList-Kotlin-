# Stage 3/6: Date and time
## Description
In this stage, we will add on another useful feature — the ability to indicate a deadline for a particular task. We will also work on a priority tag feature.

Of course, a task may come without a deadline. However, to keep things simple, a deadline is required for each task in this stage.

When users add a task, also ask them to input a date, time, and a priority tag. The latter is just one letter: `C`, `H`, `N`, `L` (case-insensitive) per task that stands for Critical, High, Normal, and Low, respectively.

Dates and time can be handled in many ways. However, in this project, we recommend using the Kotlinx `datetime` library. Kotlinx consists of valuable libraries that aren't a part of the standard library. To access the Kotlinx `datetime` library, it should be added to your project dependencies, but this has already been done for you. In order to use it, you should just import it to your program with:
```
import kotlinx.datetime.*
```
Following are some examples of how to use the datetime library:
```kotlin
// Create a LocalDate instance for 2017-4-29
val date = LocalDate(2017, 4, 29)

//Create a LocalDateTime instance for 2021-12-21 16:57
val dateTime = LocalDateTime(2021, 12, 31, 16, 57)
val year = dateTime.year         // Get year as an integer
val month = dateTime.monthNumber // Get month as an integer
val day = dateTime.dayOfMonth    // Get day as an integer
val hour = dateTime.hour         // Get hour as an integer
val minutes = dateTime.minute    // Get minutes as an integer

// Create a LocalDateTime instance for the current date and time for UTC+0 timezone
val dateTimeCurrent = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0"))
// Create a LocalDate instance for the current date and time for UTC+0 timezone
val dateCurrent = dateTimeCurrent.date

// Get the date and time as string
val dateTimeString = dateTime.toString()
// Print the string above
println(dateTimeString)  // Output 2021-12-31T16:57

// Same result as the above
println(dateTime)        // Output 2021-12-31T16:57
```

As can be seen, the `LocalDateTime` output string divides the date from the time with the character T.

Both `LocalDate` and `LocalDateTime` throw an `IllegalArgumentException` exception if the provided date or time data are invalid. They can be used for checking if a provided date is valid (based on leap years, number of days per month, etc.).

> You can refer to the <a href="https://github.com/Kotlin/kotlinx-datetime">GitHub overview</a> for more info on Kotlin/kotlinx-datetime. Note that the library is compatible with the Kotlin Standard Library starting from 1.5.0. So, better update Kotlin in your IntelliJ IDE to the latest version.

Finally, a word of caution. In stage 6, the task list data will be saved in a file in JSON format with the `Moshi` library. While the `datetime` library objects are serializable, to use them with `Moshi`, you need to register an explicit adapter. It is better to use the `datetime` library for date validation but keep the date and time data (in lists, classes, and so on.) as <b>strings</b> or <b>integers</b>.

## Objectives
The program menu remains the same.

When users input `add`, do the following:
1. Ask users for the task priority with `Input the task priority (C, H, N, L):`
2. If users fail to provide any of the `C`, `H`, `N`, `L` priorities, ask them again (case-insensitive);
3. Ask for a date with `Input the date (yyyy-mm-dd):`
4. If users don't input a valid date, print `The input date is invalid` and ask again;
5. Ask the time with `Input the time (hh:mm):`
6. If users fail to provide a valid time, print `The input time is invalid` and ask again;
7. Ask users to input a task with `Input a new task (enter a blank line to end):` as in the previous stage;
8. If one blank line is provided, print `The task is blank` and don't save the task.

Note that the date should be a real date in the following format:
```
<year (4 digits)>-<number of month>-<number of day in the month>
```

Time should be in the following format:
```
<hour (0 - 23)>:<minutes (0 - 59)>

```
When the user inputs `print`, print the tasks as in the previous stage but with one difference — add the date, time, and priority to the first line for each task:
```
1  Date1 Time1 Priority1
   Task1-line1
   Task1-line2

2  Date2 Time2 Priority2
   Task2-line1

3  Date3 Time3 Priority3
   Task3-line1

4  Date4 Time4 Priority4
   Task4-line1
   Task4-line2
```

Separate the date, time, and priority by a single space.

If the number of months, number of days in the month, hour, or minutes is only one digit, they must be preceded by a leading zero. In this way, each of them will always be printed as a two-digit number.

Print a year as a four-digit number.

## Examples
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

<b>Example 1:</b> <i>normal execution</i>
```
Input an action (add, print, end):
> add
Input the task priority (C, H, N, L):
> H
Input the date (yyyy-mm-dd):
> 2021-12-25
Input the time (hh:mm):
> 14:00
Input a new task (enter a blank line to end):
> Christmas meal
>
Input an action (add, print, end):
> add
Input the task priority (C, H, N, L):
> n
Input the date (yyyy-mm-dd):
> 2022-1-12
Input the time (hh:mm):
> 19:15
Input a new task (enter a blank line to end):
> Dentist
>
Input an action (add, print, end):
> add
Input the task priority (C, H, N, L):
> L
Input the date (yyyy-mm-dd):
> 2021-12-23
Input the time (hh:mm):
> 9:0
Input a new task (enter a blank line to end):
> Supermarket
> -----------
> Pasta
> Butter
> Cheese
>
Input an action (add, print, end):
> print
1  2021-12-25 14:00 H
Christmas meal

2  2022-01-12 19:15 N
Dentist

3  2021-12-23 09:00 L
Supermarket
   -----------
Pasta
Butter
Cheese

Input an action (add, print, end):
> end
Tasklist exiting!
```

<b>Example 2:</b> <i>wrong priority</i>
```
Input an action (add, print, end):
> add
Input the task priority (C, H, N, L):
Critical
Input the task priority (C, H, N, L):
> normal
Input the task priority (C, H, N, L):
> n
Input the date (yyyy-mm-dd):
> 2022-1-1
Input the time (hh:mm):
> 12:00
Input a new task (enter a blank line to end):
> Call friends for new year greetings
>
Input an action (add, print, end):
> end
Tasklist exiting!
```

<b>Example 3:</b> <i>wrong date</i>
```
Input an action (add, print, end):
> add
Input the task priority (C, H, N, L):
> N
Input the date (yyyy-mm-dd):
> 2021-2-29
The input date is invalid
Input the date (yyyy-mm-dd):
> 2021-13-1
The input date is invalid
Input the date (yyyy-mm-dd):
> 2021-12-32
The input date is invalid
Input the date (yyyy-mm-dd):
> 2021-12-31
Input the time (hh:mm):
> 12:00
Input a new task (enter a blank line to end):
> Supermarket
>
Input an action (add, print, end):
> end
Tasklist exiting!
```

<b>Example 4:</b> <i>wrong time</i>
```
Input an action (add, print, end):
> add
Input the task priority (C, H, N, L):
> n
Input the date (yyyy-mm-dd):
> 2021-12-23
Input the time (hh:mm):
> 24:00
The input time is invalid
Input the time (hh:mm):
> 17:60
The input time is invalid
Input the time (hh:mm):
> 9:15
Input a new task (enter a blank line to end):
> Supermarket
>
Input an action (add, print, end):
> end
Tasklist exiting!
```

<b>Example 5:</b> <i>blank task</i>
```
Input an action (add, print, end):
> add
Input the task priority (C, H, N, L):
> H
Input the date (yyyy-mm-dd):
> 2021-11-11
Input the time (hh:mm):
> 9:15
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
