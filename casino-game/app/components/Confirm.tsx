import { useEffect } from 'react';
import axios from 'axios';
import { useRouter } from 'next/router';

export default function ConfirmPage() {
    const router = useRouter();
    const { token } = router.query;

    useEffect(() => {
        if (token) {
            // Enviar o token para o backend para confirmar o registro
            axios.get(`http://127.0.0.1:8081/api/users/confirm?token=${token}`)
                .then(response => {
                    console.log(response.data);
                    alert('Email confirmed successfully! You can now log in.');
                    router.push('/login'); // Redirecionar para a página de login ou outra página relevante
                })
                .catch(error => {
                    console.error('Confirmation error:', error);
                    alert('Failed to confirm email.');
                });
        }
    }, [token]);

    return (
        <div className="flex justify-center items-center h-screen">
            <h2>Confirming your email...</h2>
        </div>
    );
}
