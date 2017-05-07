# 8gamesolver
8-tile "sliding puzzle game" solver

___ V1.1 OUT NOW

In this update:
– Smarter generation and searching. The code now implements a queue based breadth-first tree generation and search rather than the old recursion based version. This leads to shorter solutions and drastically reduced load time (plus, no need to run the program with -Xss100m anymore ;) ).

— More user friendlinessessessessess. Root and desired states now read from command line dynamically instead of being taken from args at start of run time. User has option to force a time limit on how long the code will search for a solution for (useful as if the user enters a state that cannot be reached the code will otherwise run for over an hour).

– Removed outdated / legacy code. Source files should now be more readable.


~v1.0 info~

What it does (v1.0 only, v1.1 runs as detailed above): Generates all reachable puzzle states from users first input, then looks for second input in those solutions. If found, prints out the path to reach that state (aka a solution to the puzzle).

Current status: Compiles, runs, horribly inefficient solutions and hour long load time.
