
import { useState } from 'react';

interface LoginPopupProps {
  togglePopup: () => void;
}

export default function LoginPopup({ togglePopup }: LoginPopupProps) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      console.log('Login attempt:', { email, password });
    } catch (error) {
      console.error('Login error:', error);
    }
  };

  return (
    <div className="fixed inset-0 flex justify-center items-center bg-custom-color">
      <div className="bg-white p-8 rounded-lg shadow-lg w-96">
        <h2 className="text-2xl mb-6 text-center">SIGN IN</h2>
        <form onSubmit={handleLogin} className="flex flex-col space-y-4">
          <div>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="border border-gray-300 p-2 w-full rounded"
              placeholder="Email"
            />
          </div>
          <div>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="border border-gray-300 p-2 w-full rounded"
              placeholder="Password"
            />
          </div>
          <button
            type="submit"
            className="text-white p-2 rounded-full btn-primary hover:from-orange-500 hover:to-red-600 transition transform hover:scale-105"
          >
            Login
          </button>
        </form>
        <button
          onClick={togglePopup}
          className="block mt-4 mx-auto text-blue-500 hover:underline"
        >
          Sign Up
        </button>
      </div>
    </div>
  );
}
