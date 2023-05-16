/*
Given an asyncronous function fn and a time t in milliseconds, return a new time limited version of the input function.

A time limited function is a function that is identical to the original unless it takes longer than t milliseconds to fullfill. 
In that case, it will reject with "Time Limit Exceeded".  Note that it should reject with a string, not an Error.

 
Example 1:
    Input: 
    fn = async (n) => { 
      await new Promise(res => setTimeout(res, 100)); 
      return n * n; 
    }
    inputs = [5]
    t = 50
    Output: {"rejected":"Time Limit Exceeded","time":50}
    Explanation:
    The provided function is set to resolve after 100ms. However, the time limit is set to 50ms. It rejects at t=50ms because the time limit was reached.

Example 2:
    Input: 
    fn = async (n) => { 
      await new Promise(res => setTimeout(res, 100)); 
      return n * n; 
    }
    inputs = [5]
    t = 150
    Output: {"resolved":25,"time":100}
    Explanation:
    The function resolved 5 * 5 = 25 at t=100ms. The time limit is never reached.

Example 3:
    Input: 
    fn = async (a, b) => { 
      await new Promise(res => setTimeout(res, 120)); 
      return a + b; 
    }
    inputs = [5,10]
    t = 150
    Output: {"resolved":15,"time":120}
    Explanation:
    The function resolved 5 + 10 = 15 at t=120ms. The time limit is never reached.

Example 4:
    Input: 
    fn = async () => { 
      throw "Error";
    }
    inputs = []
    t = 1000
    Output: {"rejected":"Error","time":0}
    Explanation:
    The function immediately throws an error.
 

Constraints:
    0 <= inputs.length <= 10
    0 <= t <= 1000
    fn returns a promise
*/

JavaScript:
/**
 * @param {Function} fn
 * @param {number} t
 * @return {Function}
 */
var timeLimit = function(fn, t) {
	return async function(...args) {
        return new Promise((resolve,reject) =>{setTimeout(()=>{
            reject("Time Limit Exceeded");
            }, t);
            fn(...args).then(resolve).catch(reject);
         });
    }
};

/**
 * const limited = timeLimit((t) => new Promise(res => setTimeout(res, t)), 100);
 * limited(150).catch(console.log) // "Time Limit Exceeded" at t=100ms
 */

TypeScript:
type Fn = (...params: any[]) => Promise<any>;

function timeLimit(fn: Fn, t: number): Fn {
	return async function(...args) {
        return new Promise((resolve,reject) =>{setTimeout(()=>{
            reject("Time Limit Exceeded");
            }, t);
            fn(...args).then(resolve).catch(reject);
         });
    }
};

/**
 * const limited = timeLimit((t) => new Promise(res => setTimeout(res, t)), 100);
 * limited(150).catch(console.log) // "Time Limit Exceeded" at t=100ms
 */
