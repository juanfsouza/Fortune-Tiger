import { useState } from 'react';
import axios from 'axios';

interface SignupPopupProps {
    togglePopup: () => void;
}

const API_URL = 'http://127.0.0.1:8081/api/users/register';

export default function SignupPopup({ togglePopup }: SignupPopupProps) {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const handleSignup = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await axios.post(API_URL, { name, email, password });
            setMessage(response.data); // Exibir a mensagem de sucesso
        } catch (error) {
            console.error('Signup error:', error);
            setMessage('An error occurred while registering.'); // Exibir a mensagem de erro
        }
    };

    return (
        <div className="fixed inset-0 flex justify-center items-center bg-custom-color">
            <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
                <h2 className="text-2xl mb-6 text-center">Sign Up</h2>
                <form onSubmit={handleSignup} className="flex flex-col space-y-4">
                    <div>
                        <input
                            type="text"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            className="border border-gray-300 p-2 w-full rounded"
                            placeholder="Enter your name"
                        />
                    </div>
                    <div>
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            className="border border-gray-300 p-2 w-full rounded"
                            placeholder="Enter your email"
                        />
                    </div>
                    <div>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            className="border border-gray-300 p-2 w-full rounded"
                            placeholder="Enter your password"
                        />
                    </div>
                    <button
                        type="submit"
                        className="btn-primary text-white p-2 rounded-full hover:from-orange-500 hover:to-red-600 transition transform hover:scale-105"
                    >
                        Register
                    </button>
                </form>
                {message && <p className="mt-4 text-center">{message}</p>}
                <button
                    onClick={togglePopup}
                    className="block mt-4 mx-auto text-blue-500 hover:underline"
                >
                    Login
                </button>
            </div>
        </div>
    );
}
