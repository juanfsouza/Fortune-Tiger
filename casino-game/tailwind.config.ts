
module.exports = {
  content: [
    "./app/**/*.{js,ts,jsx,tsx}",
    "./components/**/*.{js,ts,jsx,tsx}",
    "./pages/**/*.{js,ts,jsx,tsx}",
    "./public/**/*.html",
  ],
  theme: {
    extend: {
      colors: {
        primary: {
          light: '#FFA726',
          DEFAULT: '#FF7043',
          dark: '#F4511E', 
        },
        bg: {
          950: '#0a0a0a', 
        },
      },
      backgroundImage: {
        'gradient-primary': 'linear-gradient(to right, #FFA726, #F4511E)',
      },
    },
  },
  plugins: [],
};
