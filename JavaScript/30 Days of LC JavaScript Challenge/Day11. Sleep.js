/*
Given a positive integer millis, write an asyncronous function that sleeps for millis milliseconds. It can resolve any value.

 
Example 1:
    Input: millis = 100
    Output: 100
    Explanation: It should return a promise that resolves after 100ms.
    let t = Date.now();
    sleep(100).then(() => {
      console.log(Date.now() - t); // 100
    });

Example 2:
    Input: millis = 200
    Output: 200
    Explanation: It should return a promise that resolves after 200ms.
 

Constraints:
    1 <= millis <= 1000
*/

JavaScript:
/**
 * @param {number} millis
 */
async function sleep(millis) {
    await new Promise(resolve =>{ setTimeout(resolve, millis)});
}

/** 
 * let t = Date.now()
 * sleep(100).then(() => console.log(Date.now() - t)) // 100
 */

TypeScript:

async function sleep(millis: number): Promise<void> {
    await new Promise(resolve =>{ setTimeout(resolve, millis)});
}


/** 
 * let t = Date.now()
 * sleep(100).then(() => console.log(Date.now() - t)) // 100
 */
