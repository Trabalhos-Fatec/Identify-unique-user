export const TOKEN_KEY = "token";
export const isAuthenticated = () => localStorage.getItem(TOKEN_KEY) !== null;
export const getToken = () => localStorage.getItem(TOKEN_KEY);
export const login = token => {
  localStorage.setItem(TOKEN_KEY, token);
};

export const Loggedout = () => {
  localStorage.removeItem(TOKEN_KEY);
};

export const hasUser = response => {
  localStorage.setItem('userName', response);
};

export const getUser = () => {
  localStorage.getItem('userName');
};