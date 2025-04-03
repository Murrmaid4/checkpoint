import React, { useState } from "react";
import { useNavigate, useParams} from "react-router";
import { PencilIcon, TrashIcon } from "@heroicons/react/24/solid";
import { Dialog, DialogTrigger, DialogContent, DialogTitle, DialogDescription } from "@radix-ui/react-dialog";




const GameLogCard = ({ game, status, notes, log_date, id, onDelete }) => {
  const navigate = useNavigate();
  const [isDeleteOpen, setIsDeleteOpen] = useState(false);


  const handleDelete = () => {
    fetch(`http://localhost:8080/api/log/${id}`, { method: "DELETE" })
        .then((res) => {
            if (res.ok) {
                onDelete(id);
                setIsDeleteOpen(false);
            } else {
                return res.json();
            }
        })
        .catch((err) => setError("Failed to delete log."));
      
      }
  return (
    <div className="mb-4 relative p-4 bg-gray-800 text-white rounded-lg shadow-md transition-transform hover:scale-105 cursor-pointer">
      {/* Edit & Delete Icons */}
      <div className="absolute top-2 right-2 flex space-x-2">
        <button className="p-1 text-blue-400 hover:text-blue-500" onClick={() => navigate(`/edit/${id}`)}>
          <PencilIcon className="w-5 h-5" />
        </button>
        <Dialog open={isDeleteOpen} onOpenChange={setIsDeleteOpen}>
          <DialogTrigger asChild>
            <button className="p-1 text-red-400 hover:text-red-500">
              <TrashIcon className="w-5 h-5" />
            </button>
          </DialogTrigger>
          <DialogContent className="bg-gray-900 text-white p-6 rounded-lg shadow-lg max-w-md mx-auto">
            <DialogTitle className="text-xl font-bold">Confirm Delete</DialogTitle>
            <DialogDescription className="text-gray-400">
              Are you sure you want to delete <strong>{game.title}</strong>? This action cannot be undone.
            </DialogDescription>
            <div className="flex justify-end mt-6 space-x-4">
              <button className="px-4 py-2 rounded-md bg-gray-700 hover:bg-gray-600" onClick={() => setIsDeleteOpen(false)}>
                Cancel
              </button>
              <button 
                className="px-4 py-2 rounded-md bg-red-600 hover:bg-red-700" 
                onClick={() => {
               
                  handleDelete({id});
                  setIsDeleteOpen(false);
                }}
              >
                Delete
              </button>
            </div>
          </DialogContent>
        </Dialog>
      </div>

      {/* Game Title & Date */}
      <h2 className="text-xl font-bold">{game.title}</h2>
      <p className="text-sm text-gray-400">{log_date}</p>

      {/* Status Badge */}
      <span
        className={`inline-block px-3 py-1 mt-2 text-sm font-medium rounded-full ${
          status === "COMPLETED"
            ? "bg-green-600"
            : status === "PLAYING"
            ? "bg-blue-600"
            : status === "ON_HOLD"
            ? "bg-red-600"
            : "bg-yellow-600"
        }`}
      >
        {status}
      </span>

      {/* Notes */}
      <p className="mt-3 text-gray-300 text-sm truncate">{notes}</p>
    </div>
  );
};

export default GameLogCard;
