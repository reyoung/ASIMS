define([],function (){
  function Add (a,b) {
    return a+b
  }
  function Times(a,b) {
    return a*b
  }
  retv = new Object()
  retv.Add = Add
  retv.Times = Times
  return retv
})