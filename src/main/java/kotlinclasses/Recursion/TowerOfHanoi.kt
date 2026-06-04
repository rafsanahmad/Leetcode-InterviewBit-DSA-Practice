/*
 *
 *  * TowerOfHanoi.kt
 *  *
 *  * Created by Rafsan Ahmad on 06/01/26, 12:56 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package kotlinclasses.Recursion

class TowerOfHanoi {
    /*Problem
You have:

3 rods: Source (A), Auxiliary (B), Destination (C)
n disks of different sizes stacked on A (largest at bottom)
Goal: Move all disks from A to C
Rules
Move only one disk at a time.
A larger disk can never be placed on a smaller disk.
Only the top disk can be moved.
Example: n = 3

Initial:

A: 3 2 1
B:
C:

Goal:

A:
B:
C: 3 2 1
Recursive Thinking

Suppose we have 3 disks:

A: [3,2,1]

To move Disk 3 (largest) from A → C:

Step 1

Move top 2 disks from A → B.

A: [3]
B: [2,1]
C:
Step 2

Move Disk 3 from A → C.

A:
B: [2,1]
C: [3]
Step 3

Move 2 disks from B → C.

A:
B:
C: [3,2,1]

Notice something:

To move 3 disks, we needed to solve the same problem for 2 disks.

This is recursion.

Recursive Formula

To move n disks from Source → Destination:

Base Case

If n == 1

Move directly.

Recursive Case
Move n-1 disks from Source → Auxiliary
Move nth disk from Source → Destination
Move n-1 disks from Auxiliary → Destination
Visualization for n=3
Hanoi(3, A, C, B)

1. Hanoi(2, A, B, C)

   1. Hanoi(1, A, C, B)
      Move A -> C

   Move A -> B

   Hanoi(1, C, B, A)
      Move C -> B

2. Move A -> C

3. Hanoi(2, B, C, A)

   Hanoi(1, B, A, C)
      Move B -> A

   Move B -> C

   Hanoi(1, A, C, B)
      Move A -> C*/

    /*Time Complexity
Let T(n) be the number of moves.

Recurrence:
T(n)=2T(n−1)+1
Expanding:
T(n)
= 2(2T(n-2)+1)+1
= 4T(n-2)+3
= 8T(n-3)+7
...

Result:
T(n)=2^n−1

So:
Time Complexity: O(2ⁿ)
Space Complexity: O(n) (recursive call stack)*/
    fun towerOfHanoi(
        n: Int,
        source: Char,
        destination: Char,
        auxiliary: Char
    ) {
        if (n == 1) {
            println("Move disk 1 from $source to $destination")
            return
        }

        // Move n-1 disks to auxiliary
        towerOfHanoi(
            n - 1,
            source,
            auxiliary,
            destination
        )

        // Move largest disk
        println("Move disk $n from $source to $destination")

        // Move n-1 disks to destination
        towerOfHanoi(
            n - 1,
            auxiliary,
            destination,
            source
        )
    }
}

fun main() {
    val obj = TowerOfHanoi()
    obj.towerOfHanoi(3, 'A', 'C', 'B')
}