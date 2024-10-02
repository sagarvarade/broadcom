
const localStorageGet=(key)=>{
  const loc=JSON.parse(localStorage.getItem('auth'));
  if(loc!==null && (loc[key]!=='' || loc[key]!==undefined))
  {
    return loc[key];
  }
  else
    return '';
  
};

export default localStorageGet