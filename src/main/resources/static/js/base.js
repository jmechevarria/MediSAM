/**
 * If <b>this</b> is an empty object, the method returns false, else it returns <b>this</b>
 * @returns {$.fn|Boolean}
 */
$.fn.presence = function () {
    return this.length !== 0 && this;
};